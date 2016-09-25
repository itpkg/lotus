import Ember from 'ember';

export default Ember.Route.extend({
  ajax: Ember.inject.service(),
  utils: Ember.inject.service(),
  actions: {
    forgotPassword() {
      var user = {
        email: this.get('controller.email')
      };
      this.get('ajax').post('/users/forgot-password', {data: user})
        .then(function() {
          this.transitionTo('/users/sign-in');
        }.bind(this)).catch(this.get('utils').failed);
    }
  }
});
