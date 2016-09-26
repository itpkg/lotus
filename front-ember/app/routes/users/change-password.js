import Ember from 'ember';

export default Ember.Route.extend({
  // queryParams: ['token'],
  token: '',
  ajax: Ember.inject.service(),
  utils: Ember.inject.service(),
  beforeModel(params){
    this.set('token', params.queryParams.token);
  },
  actions: {
    changePassword() {
      var user = {
        password: this.get('controller.password'),
        passwordConfirm: this.get('controller.passwordConfirm'),
        token: this.get('token')
      };

      this.get('ajax').post('/users/change-password', {data: user})
        .then(function() {
          this.transitionTo('/users/sign-in');
        }.bind(this)).catch(this.get('utils').failed);
    }
  }
});
