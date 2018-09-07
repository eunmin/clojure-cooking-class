# Emacs에서 문법 하이라이팅

by Eunmin Kim

## 이럴 때 쓰세요

편집기로 Emacs를 사용하려고 하는데 클로저 문법을 하이라이팅 해서 보고 싶습니다.

## 어떻게 하나요?

### 1. clojure-mode 설치하기

`clojure-mode` 패키지가 클로저 문법 하이라이팅을 해줍니다. `clojure-mode`는
[MELPA](https://melpa.org/)나 [MELPA Stable](http://stable.melpa.org/)에 있기 때문에
`package.el` 아카이브에 `MELPA`나 `MELPA Stable`를 추가해줍니다.

```elisp
(require 'package)
(add-to-list 'package-archives '("melpa" . "http://melpa.milkbox.net/packages/") t)
```

`clojure-mode` 패키지를 설치합니다.

```
M-x package-install [RET] clojure-mode [RET]
```

### 2. 클로저 파일 열어 확인해보기

Emacs에서 클로저 파일을 열어 확인해 봅니다.

## 이런 것도 보세요

* 패키지 및 설정을 더 편리하게 할 수 있는 [use-package](https://github.com/jwiegley/use-package)
* 중첨된 괄호를 다른 색으로 표시해주는 [rainbow-delimiters](https://github.com/Fanael/rainbow-delimiters)
