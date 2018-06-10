import $ from 'jquery';
import 'jquery-i18n';

const ajaxDefaultOptions = {
  url: undefined,
  param: {},
  dataType: undefined,
  contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
  cache: undefined,
  processData: undefined,
  type: 'POST',
  before: undefined,
  success: undefined,
  failed: undefined,
  after: undefined
};

const Common = {
	getFullPath(path) {
    if (path) {
		  return '/' + path;
    } else {
      return '/';
    }
	},
	setLanguage(message, callBack) {
		// 다국어 지원
		$.i18n.properties({
			name : message,
			mode : 'map',
			path: '/getLanguage',
			async : false,
			callback : function() {
				if (jQuery.isFunction(callBack)) {
					callBack();
				}
			}
		});
	},
	getMessage(message) {
		return $.i18n.prop(message);
	},
	pageMove(path) {
		$(location).attr('href', "/" + path);
	},
  isSuccess(data) {
    return data == true || data == 'true';
  },
  containsCharsOnly(input,chars) {
      for (var inx = 0; inx < input.length; inx++) {
         if (chars.indexOf(input.charAt(inx)) == -1)
             return false;
      }
      return true;
  },
  isUpperCase(input) {
    return Common.containsCharsOnly(input, "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
  },
  isContainUpperCase(input) {
    for (var inx = 0; inx < input.length; inx++) {
       if ("ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(input.charAt(inx)) != -1)
           return true;
    }
    return false;
  },
  isLowerCase(input) {
    return containsCharsOnly(input, "abcdefghijklmnopqrstuvwxyz");
  },
	initData($parentId) {
    $parentId.find('input[type="text"], input[type="password"]').val('');
    $parentId.find('textarea').val('');
  },
  setData($parentId, data) {
    for( let key in data ) {
      const target = $parentId.find(`[name="${key}"]`);
      if( 1 === target.length ) {
        if( 'checkbox' === target.prop('type') ) {
          if( data[key] == target.val() ) {
            target.prop('checked', true);
          }
        } else {
          target.val(data[key]).trigger('keyup');
        }
      } else if ( 1 < target.length ) {
        $parentId.find(`[name="${key}"][value="${data[key]}"]`).prop('checked', true).trigger('change');
      }
    }
  },
  setText($parentId, data) {
    for( let key in data ) {
      const target = $parentId.find(`[name="${key}"]`);
      if( 1 === target.length ) {
        if( 'checkbox' === target.prop('type') ) {
          if( data[key] == target.val() ) {
            target.prop('checked', true);
          }
        } else {
          target.text(data[key]).trigger('keyup');
        }
      } else if ( 1 < target.length ) {
        $parentId.find(`[name="${key}"][value="${data[key]}"]`).prop('checked', true).trigger('change');
      }
    }
  },
  getData($parentId) {
    let result = {};
    $parentId.find('input[type="text"], input[type="hidden"], input[type="password"], select, textarea').each((i, e) => {
      if( e.name ) {
        result[e.name] = e.value;
      }
    });
    $parentId.find('input[type="checkbox"], input[type="radio"]').each((i, e) => {
      if( e.checked ) {
        result[e.name] = e.value;
      }
    });
    $parentId.find('input[type="file"]').each((i, e) => {
      if( e.name ) {
        result[e.name] = e.files[0];
      }
    });
    return result;
  },

  ipValidate(input) {
    if (/^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/.test(input)) {
      return true;
    }
    return false;
  },
  emailValidate(input) {
    return /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i.test(input);
  },
  passwordValidate(input) {
    return input && Common.isContainUpperCase(input) && input.length > 7;
  },
  telValidate(input) {
    return /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/.test(input);
  },
  sendAjax(options) {
    for( var i in ajaxDefaultOptions ) {
      if( undefined === options[i] ) {
        options[i] = ajaxDefaultOptions[i];
      }
    }

    const executeAjax = function() {
      $.ajax({
        type: options.type,
        url: options.url,
        data : options.param,
        cache: options.cache,
        dataType: options.dataType,
        contentType : options.contentType,
        processData: options.processData,
        success: options.success,
        error: options.failed,
        complete: options.after
      });
    };

    if( options.before ) {
      if( !options.before() ) {
        return;
      }
    }

    if( options.beforeCallback ) {
      options.beforeCallback(executeAjax);
    } else {
      executeAjax();
    }
  }
};

export default Common;
