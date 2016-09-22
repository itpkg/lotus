class Mail::DomainsController < ApplicationController
  before_action :must_admin!

  def new
    @domain = Mail::Domain.new
    render 'form'
  end

  def create
    @domain = Mail::Domain.new params.require(:mail_domain).permit(:name)
    if @domain.save
      flash[:notice] = ' '
      redirect_to mail_root_path
    else
      flash[:alert] = @domain.errors
      render 'form'
    end

  end

  def edit
    @domain = Mail::Domain.find params[:id]
    render 'form'
  end

  def update
    @domain = Mail::Domain.find params[:id]
    if @domain.update params.require(:mail_domain).permit(:name)
      flash[:notice] = ' '
      redirect_to mail_root_path
    else
      flash[:alert] = @domain.errors
      render 'form'
    end

  end


  def destroy
    domain = Mail::Domain.find params[:id]
    if domain.users.count ==0 && domain.aliases.count == 0
      flash[:notice] = ' '
      domain.destroy
    else
      flash[:alert] = ' '
    end
    redirect_to mail_root_path
  end
end
