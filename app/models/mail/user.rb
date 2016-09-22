class Mail::User < ApplicationRecord
  belongs_to :domain, class_name: 'Mail::Domain'

  validates :domain_id, :password, presence: true

  validates :email, presence: true, uniqueness: true

  def password!(password)
    self.password = self.ssha512 password, SecureRandom.hex(32)
  end

  def check?(password)
    "doveadm pw -t {SSHA512}#{self.password} -p #{password}"
  end

  def password?(password)
    self.password == self.ssha512(password, Base64.strict_decode64(self.password)[-64..-1])
  end

  def ssha512(password, salt)
    Base64.strict_encode64(Digest::SHA512.digest(password+salt) + salt)
  end
end
