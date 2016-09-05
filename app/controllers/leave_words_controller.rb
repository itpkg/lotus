class LeaveWordsController < ApplicationController

  def create
    lw = LeaveWord.create params.require(:leave_word).permit(:content)
    if lw.valid?
      flash[:notice] = ' '
    else
      flash[:alert] = lw.errors
    end
    redirect_back fallback_location: params[:back]
  end

  def destroy
    lw = LeaveWord.find params[:id]
    authorize lw, :destroy?
    lw.destroy

    redirect_to leave_words_path
  end

  def index
    @items = LeaveWord.order(id: :desc).page params[:page]
    authorize @items, :index?
    render layout: 'dashboard'
  end
end