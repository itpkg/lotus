# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rails db:seed command (or created alongside the database with db:setup).
#
# Examples:
#
#   movies = Movie.create([{ name: 'Star Wars' }, { name: 'Lord of the Rings' }])
#   Character.create(name: 'Luke', movie: movies.first)
email, password = 'admin@change-me.com', 'change-me'

root = User.new email: email, password: password, password_confirmation: password
root.skip_confirmation!
root.save!

[:admin, :root].each { |r| root.add_role r }