class Mail::AliasesController < ApplicationController
  before_action :must_admin!

  def new
    @alias = Mail::Alias.new
    render 'form'
  end

  def create
    @alias = Mail::Alias.new params.require(:mail_alias).permit(:source, :destination, :domain_id)
    if @alias.save
      flash[:notice] = ' '
      redirect_to mail_root_path
    else
      flash[:alert] = @alias.errors
      render 'form'
    end

  end

  def edit
    @alias = Mail::Alias.find params[:id]
    render 'form'
  end

  def update
    @alias = Mail::Alias.find params[:id]
    if @alias.update params.require(:mail_alias).permit(:source, :destination, :domain_id)
      flash[:notice] = ' '
      redirect_to mail_root_path
    else
      flash[:alert] = @alias.errors
      render 'form'
    end

  end


  def destroy
    @alias = Mail::Alias.find params[:id]
    @alias.destroy
    redirect_to mail_root_path
  end
end
