class NoticesController < ApplicationController
  before_action :must_admin!

  def new
    @notice = Notice.new
    render 'form'
  end

  def create
    @notice = Notice.create params.require(:notice).permit(:content)

    if @notice.valid?
      redirect_to notices_path
    else
      flash[:alert] = @notice.errors
      render 'form'
    end
  end

  def destroy
    n = Notice.find params[:id]
    n.destroy
    redirect_to notices_path
  end

  def edit
    @notice = Notice.find params[:id]
    render 'form'
  end

  def update
    @notice = Notice.find params[:id]
    @notice.update params.require(:notice).permit(:content)
    if @notice.valid?
      redirect_to notices_path
    else
      flash[:alert] = @notice.errors
      render 'form'
    end
  end

  def index
    @notices = Notice.order(updated_at: :desc).page params[:page]
  end
end