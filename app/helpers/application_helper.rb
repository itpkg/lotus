require 'html/pipeline'

module ApplicationHelper
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
