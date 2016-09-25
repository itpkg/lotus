import Ember from 'ember';
import config from './config/environment';

const Router = Ember.Router.extend({
  location: config.locationType,
  rootURL: config.rootURL
});

Router.map(function() {
  this.route('users', function() {
    this.route('sign-in');
    this.route('sign-up');
    this.route('confirm');
    this.route('unlock');
    this.route('profile');
    this.route('forgot-password');
    this.route('change-password');
  });
  this.route('install');
});

export default Router;
