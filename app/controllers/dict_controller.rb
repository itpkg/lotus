class DictController < ApplicationController
  def index
    db = "#{Rails.root}/tmp/dict"
    # @info = `sdcv --data-dir=#{db} -l`
    case request.method_symbol
      when :post
        kw = params[:keyword]
        if kw =~ /^\p{Word}+$/u
          @result = `sdcv --data-dir=#{db} #{kw}`
        else
          flash[:alert] = t 'errors.bad_input'
        end
      else

    end
  end
end
