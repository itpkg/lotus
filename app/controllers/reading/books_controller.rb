class Reading::BooksController < ApplicationController
  def index
    @books = Reading::Book.order(rate: :desc).page params[:page]
  end
end
