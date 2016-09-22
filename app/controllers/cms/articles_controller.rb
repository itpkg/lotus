module Cms
  class ArticlesController < ApplicationController
    def new
      @article = Cms::Article.new
      authorize @article
      render 'form'
    end

    def create
      @article = Cms::Article.new params.require(:cms_article).permit(:title, :body, tag_ids: [])
      authorize @article
      @article.user = current_user
      if @article.save
        redirect_to cms_article_path(@article)
      else
        render 'form'
      end
    end

    def show
      @article = Cms::Article.find params[:id]
    end

    def edit
      @article = Cms::Article.find params[:id]
      authorize @article
      render 'form'
    end

    def update
      @article = Cms::Article.find params[:id]
      authorize @article
      if @article.update params.require(:cms_article).permit(:title, :body, tag_ids: [])
        redirect_to cms_article_path(@article)
      else
        render 'form'
      end
    end

    def destroy
      @article = Cms::Article.find params[:id]
      authorize @article
      @article.destroy
      redirect_to cms_articles_path
    end

    def index
      @articles = Cms::Article.order(updated_at: :desc).page params[:page]
    end
  end
end
