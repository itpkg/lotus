# http://wiki2.dovecot.org/HowTo/DovecotPostgresql
# https://www.linode.com/docs/email/postfix/email-with-postfix-dovecot-and-mysql
# http://www.tunnelsup.com/using-salted-sha-hashes-with-dovecot-authentication
class Mail::HomeController < ApplicationController
  before_action :must_admin!, except: [:change_password]

  def change_password
    case request.method_symbol
      when :post
        password = params[:new_password]
        if !password.empty? && password == params[:re_password]
          user = Mail::User.where(email: params[:email]).first
          if !user.nil? && user.password?(params[:old_password])
            user.password! password
            user.save
            flash[:notice] = ' '
            return
          end
        end
        flash[:alert] = t 'errors.bad_input'
        
      else
    end
  end

  def postfix
    # todo
  end

  def dovecot
# todo
  end
end
