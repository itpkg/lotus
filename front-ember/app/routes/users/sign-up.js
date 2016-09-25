import Ember from 'ember';

export default Ember.Route.extend({
  ajax: Ember.inject.service(),
  i18n: Ember.inject.service(),
  actions: {
    signUp() {
      var user = {
        name: this.get('controller.name'),
        email: this.get('controller.email'),
        password: this.get('controller.password'),
        re_password: this.get('controller.re_password')
      };
      this.get('ajax').post('/users/sign-up', {data: user})
        .then(function(rst) {
          alert(this.get('i18n').t('messages.success'));
        }).catch(function(e) {
          alert(e.message);
        });
    }
  }
});
