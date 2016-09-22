module Cms
  class CommentsController < ApplicationController

    def new
      @comment = Cms::Comment.new
      @comment.article_id = params[:article_id]
      render 'form'
    end

    def create
      @comment = Cms::Comment.new params.require(:cms_comment).permit(:body, :article_id)
      @comment.user = current_user
      authorize @comment
      if @comment.save
        redirect_to cms_article_path(@comment.article_id)
      else
        render 'form'
      end
    end

    def edit
      @comment = Cms::Comment.find params[:id]
      authorize @comment
      render 'form'
    end

    def update
      @comment = Cms::Comment.find params[:id]
      authorize @comment
      if @comment.update params.require(:cms_comment).permit(:body)
        redirect_to cms_article_path(@comment.article_id)
      else
        render 'form'
      end
    end

    def destroy
      @comment = Cms::Comment.find params[:id]
      authorize @comment
      @comment.destroy
      redirect_to cms_article_path(@comment.article_id)
    end


    def index
      @comments = Cms::Comment.order(updated_at: :desc).page params[:page]
    end
  end
end
