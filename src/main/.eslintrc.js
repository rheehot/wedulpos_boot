module.exports = {
  "env": {
    "browser": true,
    "es6": true
  },
  "extends": "eslint:recommended",
  "rules": {
    "no-console": 0,
    "indent": [
      "error",
      2
    ],
    "quotes": [
      "error",
      "single"
    ],
    "semi": [
      "error",
      "always"
    ]
  },
  "parserOptions": {
    "sourceType": "module"
  },
  "globals": {
    "getMessage": true,
    "getPath": true
  }
};
