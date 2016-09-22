class Reading::NotesController < ApplicationController
  def index
    @notes = Reading::Note.order(updated_at: :desc).page params[:page]
  end

  def new
    @note = Reading::Note.new
    @note.book_id = params[:book_id]
    authorize @note
    render 'form'
  end

  def create
    @note = Reading::Note.new params.require(:reading_note).permit(:body, :book_id)
    authorize @note
    @note.user = current_user
    if @note.save
      flash[:notice] = ' '
      redirect_to reading_book_path(@note.book_id)
    else
      flash[:alert] = @note.errors.messages
      render 'form'
    end

  end

  def edit
    @note = Reading::Note.find params[:id]
    authorize @note
    render 'form'
  end

  def update
    @note = Reading::Note.find params[:id]
    authorize @note
    if @note.update params.require(:reading_note).permit(:body)
      flash[:notice] = ' '
      redirect_to reading_book_path(@note.book_id)
    else
      flash[:alert] = @note.errors.messages
      render 'form'
    end

  end


  def destroy
    note = Reading::Note.find params[:id]
    authorize note
    note.destroy
    redirect_to reading_book_path(@note.book_id)
  end
end
