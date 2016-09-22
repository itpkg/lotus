module Cms
  class ArticlePolicy < ApplicationPolicy
    def update?
      !user.nil? && (record.user.id == user.id || user.is_admin?)
    end

    def create?
      !user.nil?
    end

    def destroy?
      !user.nil? && (record.user.id == user.id || user.is_admin?)
    end
  end
end