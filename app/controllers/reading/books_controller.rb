class Reading::BooksController < ApplicationController
  def index
    @books = Reading::Book.order(rate: :desc).page params[:page]
  end

  def show
    bk = Reading::Book.find params[:id]
    redirect_to reading_page_path(id: bk.uid, file: bk.home)
  end

  def destroy
    book = Reading::Book.find params[:id]
    authorize book
    book.destroy
    redirect_to reading_books_path
  end
end
