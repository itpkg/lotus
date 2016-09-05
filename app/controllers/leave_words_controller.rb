class LeaveWordsController < ApplicationController

  before_action :must_admin!, only: [:destroy, :index]

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

    lw.destroy

    redirect_to leave_words_path
  end

  def index
    @items = LeaveWord.order(id: :desc).page params[:page]

  end
end