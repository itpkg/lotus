desc 'Generate sitemap.xml'
task sitemap: :environment do
  SitemapGenerator::Sitemap.default_host = "http://#{ENV['HOST']}"
  SitemapGenerator::Sitemap.create do
    add home_help_path
    add home_about_path
    add home_faq_path
  end
end