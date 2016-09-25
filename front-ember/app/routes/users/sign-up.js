import Ember from 'ember';

export default Ember.Route.extend({
  ajax: Ember.inject.service(),
  utils: Ember.inject.service(),
  actions: {
    signUp() {
      var user = {
        name: this.get('controller.name'),
        email: this.get('controller.email'),
        password: this.get('controller.password'),
        re_password: this.get('controller.re_password')
      };
      this.get('ajax').post('/users/sign-up', {data: user})
        .then(function() {
          this.transitionTo('/users/sign-in');
        }.bind(this)).catch(this.get('utils').failed);
    }
  }
});
