if (!dpr && !scale) {
    var isAndroid = win.navigator.appVersion.match(/android/gi);
    var isIPhone = win.navigator.appVersion.match(/iphone/gi);
    var devicePixelRatio = win.devicePixelRatio;
    if (isIPhone) {
       $(".content").append("<style>.gdyg-list-block .item-inner:after{height: 1px;}</style>");
    } else {
       $(".content").append("<style>.gdyg-list-block .item-inner:after{height: 2px;}</style>");
    }
}