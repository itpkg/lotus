# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 20160922032929) do

  # These are extensions that must be enabled in order to support this database
  enable_extension "plpgsql"

  create_table "leave_words", force: :cascade do |t|
    t.text     "content",    null: false
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "logs", force: :cascade do |t|
    t.string   "message",                null: false
    t.integer  "flag",       default: 0, null: false
    t.integer  "user_id"
    t.datetime "created_at",             null: false
    t.index ["user_id"], name: "index_logs_on_user_id", using: :btree
  end

  create_table "notices", force: :cascade do |t|
    t.text     "content",    null: false
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "reading_books", force: :cascade do |t|
    t.string   "title",                             null: false
    t.string   "identifier",                        null: false
    t.string   "creator",                           null: false
    t.string   "subject",                           null: false
    t.string   "language",   limit: 5,              null: false
    t.string   "publisher",                         null: false
    t.string   "date",                              null: false
    t.string   "version",                           null: false
    t.string   "uid",        limit: 36,             null: false
    t.string   "file",                              null: false
    t.string   "home",                              null: false
    t.integer  "rate",                  default: 0, null: false
    t.datetime "created_at",                        null: false
    t.datetime "updated_at",                        null: false
    t.index ["creator"], name: "index_reading_books_on_creator", using: :btree
    t.index ["file"], name: "index_reading_books_on_file", unique: true, using: :btree
    t.index ["identifier"], name: "index_reading_books_on_identifier", unique: true, using: :btree
    t.index ["language"], name: "index_reading_books_on_language", using: :btree
    t.index ["publisher"], name: "index_reading_books_on_publisher", using: :btree
    t.index ["subject"], name: "index_reading_books_on_subject", using: :btree
    t.index ["uid"], name: "index_reading_books_on_uid", unique: true, using: :btree
    t.index ["version"], name: "index_reading_books_on_version", using: :btree
  end

  create_table "reading_notes", force: :cascade do |t|
    t.text     "body",                        null: false
    t.integer  "user_id",                     null: false
    t.integer  "reading_book_id",             null: false
    t.integer  "rate",            default: 0, null: false
    t.datetime "created_at",                  null: false
    t.datetime "updated_at",                  null: false
    t.index ["reading_book_id"], name: "index_reading_notes_on_reading_book_id", using: :btree
    t.index ["user_id"], name: "index_reading_notes_on_user_id", using: :btree
  end

  create_table "roles", force: :cascade do |t|
    t.string   "name"
    t.string   "resource_type"
    t.integer  "resource_id"
    t.datetime "created_at"
    t.datetime "updated_at"
    t.index ["name", "resource_type", "resource_id"], name: "index_roles_on_name_and_resource_type_and_resource_id", using: :btree
    t.index ["name"], name: "index_roles_on_name", using: :btree
  end

  create_table "settings", force: :cascade do |t|
    t.string   "var",                   null: false
    t.text     "value"
    t.integer  "thing_id"
    t.string   "thing_type", limit: 30
    t.datetime "created_at"
    t.datetime "updated_at"
    t.index ["thing_type", "thing_id", "var"], name: "index_settings_on_thing_type_and_thing_id_and_var", unique: true, using: :btree
  end

  create_table "users", force: :cascade do |t|
    t.string   "email",                  default: "", null: false
    t.string   "encrypted_password",     default: "", null: false
    t.string   "reset_password_token"
    t.datetime "reset_password_sent_at"
    t.datetime "remember_created_at"
    t.integer  "sign_in_count",          default: 0,  null: false
    t.datetime "current_sign_in_at"
    t.datetime "last_sign_in_at"
    t.inet     "current_sign_in_ip"
    t.inet     "last_sign_in_ip"
    t.string   "confirmation_token"
    t.datetime "confirmed_at"
    t.datetime "confirmation_sent_at"
    t.string   "unconfirmed_email"
    t.integer  "failed_attempts",        default: 0,  null: false
    t.string   "unlock_token"
    t.datetime "locked_at"
    t.datetime "created_at",                          null: false
    t.datetime "updated_at",                          null: false
    t.index ["confirmation_token"], name: "index_users_on_confirmation_token", unique: true, using: :btree
    t.index ["email"], name: "index_users_on_email", unique: true, using: :btree
    t.index ["reset_password_token"], name: "index_users_on_reset_password_token", unique: true, using: :btree
    t.index ["unlock_token"], name: "index_users_on_unlock_token", unique: true, using: :btree
  end

  create_table "users_roles", id: false, force: :cascade do |t|
    t.integer "user_id"
    t.integer "role_id"
    t.index ["user_id", "role_id"], name: "index_users_roles_on_user_id_and_role_id", using: :btree
  end

  add_foreign_key "logs", "users"
  add_foreign_key "reading_notes", "reading_books"
  add_foreign_key "reading_notes", "users"
end
