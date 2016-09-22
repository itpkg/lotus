class Mail::UsersController < ApplicationController
  before_action :must_admin!
  def new
    @user = Mail::User.new
    render 'form'
  end

  def create
    @user = Mail::User.new params.require(:mail_user).permit(:email, :password, :domain_id)
    @user.password! @user.password
    if @user.save
      flash[:notice] = ' '
      redirect_to mail_root_path
    else
      flash[:alert] = @user.errors
      render 'form'
    end

  end

  def edit
    @user = Mail::User.find params[:id]
    render 'form'
  end

  def update
    @user = Mail::User.find params[:id]
    args = params.require(:mail_user).permit(:email, :password, :domain_id)
    @user.email = args[:email]
    @user.domain_id = args[:domain_id]
    @user.password! args[:password]
    if @user.save
      flash[:notice] = ' '
      redirect_to mail_root_path
    else
      flash[:alert] = @user.errors
      render 'form'
    end

  end


  def destroy
    user = Mail::User.find params[:id]
    user.destroy
    redirect_to mail_root_path
  end
end
