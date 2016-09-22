module Cms
  class TagsController < ApplicationController

    def new
      @tag = Cms::Tag.new
      authorize @tag
      render 'form'
    end

    def create
      @tag = Cms::Tag.new params.require(:tag).permit(:name)
      authorize @tag
      if @tag.save
        redirect_to tags_path
      else
        render 'form'
      end
    end

    def show
      @tag = Cms::Tag.find params[:id]
    end

    def edit
      @tag = Cms::Tag.find params[:id]
      authorize @tag
      render 'form'
    end

    def update
      @tag = Cms::Tag.find params[:id]
      authorize @tag
      if @tag.update params.require(:tag).permit(:name)
        redirect_to tags_path
      else
        render 'form'
      end
    end

    def destroy
      @tag = Cms::Tag.find params[:id]
      authorize @tag
      @tag.destroy
      redirect_to tags_path
    end
  end
end
