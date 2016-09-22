class Reading::PageController < ApplicationController
  def index
    book = Reading::Book.find params[:id]
    bk = GEPUB::Book.parse File.new(book.file)

    bk.items.each do |_, v|
      if v.href == "#{params[:file]}.#{params[:format]}"
        case v.media_type
          when 'application/xhtml+xml'
            @doc = Nokogiri::HTML v.content
          else
            send_data v.content, type: v.media_type, disposition: 'inline'
        end
      end
    end


  end
end
