namespace :books do
  namespace :download do
    desc "Download yinshun's books"
    task :yinshun do
      def download_book(id)
        `wget -P tmp "http://www.yinshun.org.tw/epub's%20web/epub/y#{sprintf "%02d", id}.epub"`
      end
      1.upto(24).each {|i| download_book i}
      42.upto(44).each {|i| download_book i}
    end
  end

  desc 'Scan epub books'
  task :scan, [:dir] => :environment do |_, args|
    root = File.join(Rails.application.root, args.dir, '**', '*.epub')
    puts "scan books from #{root}"
    Dir.glob(root).each do |file|
      puts "find file #{file}"
      book = GEPUB::Book.parse File.new(file)

      bid = book.identifier
      bk = Reading::Book.where(identifier: bid).first
      unless bk
        bk = Reading::Book.new
        bk.uid = SecureRandom.uuid
      end

      bk.title = book.title.to_s
      bk.version = book.version
      bk.creator = book.creator.to_s
      bk.identifier = bid
      bk.language = book.language.to_s
      bk.publisher = book.publisher.to_s
      bk.subject = book.subject.to_s
      bk.date = book.date.content
      bk.home = book.spine_items.first.href

      if bk.subject.empty?
        bk.subject = '-'
      end
      if bk.creator.empty?
        bk.creator = '-'
      end

      bk.file = file[Rails.root.to_s.size+1..-1]

      bk.save
      unless bk.valid?
        raise bk.errors.inspect
      end

    end

  end
end