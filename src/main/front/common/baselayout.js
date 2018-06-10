import './baselayout.scss';
import Common from 'common';
import 'fontawesome';
import 'bootstrap';

(function setMessagePath() {
  const messageUrl = ['todo','common','schedule','money','error','user','message'];
  const paths = window.location.href.split('/');
  const currPath = paths[3];

  if (currPath && messageUrl.indexOf(currPath) > -1) {
    Common.setLanguage(currPath);
  } else {
    Common.setLanguage('common');
  }
})();
