require 'html/pipeline'

module ApplicationHelper
  def top_links
    links = Setting.get_site_info :top_links
    if links.nil?
      return {}
    end
    links.split("\r\n").reduce({}) do |h, l|
      args = l.split(' ')
      if args.size == 2
        h[args.first.to_sym] = args.last
      end
      h
    end
  end

  def md2hm(md)
    # MarkdownPipeline = Pipeline.new [
    #                                     MarkdownFilter,
    #                                     SanitizationFilter,
    #                                     CamoFilter,
    #                                     ImageMaxWidthFilter,
    #                                     HttpsFilter,
    #                                     MentionFilter,
    #                                     EmojiFilter,
    #                                     SyntaxHighlightFilter
    #                                 ], {
    #                                     asset_root: "http://your-domain.com/where/your/images/live/icons",
    #                                     base_url: "http://your-domain.com",
    #                                     gfm: true
    #
    #                                 }
    filter = HTML::Pipeline::MarkdownFilter.new(md, gfm: false)
    filter.call.html_safe
  end
end
