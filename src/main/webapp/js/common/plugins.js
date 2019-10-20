/**
 * 선택한 form 객체안에 input 객체들의 값을 배열로 묶어서 던져준다.
 */
$.fn.serializeObject = function() {
    var obj = {};
    try {
        if (this[0].tagName && "FORM" == this[0].tagName.toUpperCase()) {
            var arr = this.serializeArray();
            if (arr) {
                obj = {};
                $.each(arr, function() {
                    if (obj[this.name]) {
                        obj[this.name] = obj[this.name] + "," + this.value;
                    } else {
                        obj[this.name] = this.value;
                    }
                });
            }// if (arr) {
        }
    } catch (e) {
        obj = {};
    } finally {
        ;
    }
    return obj;
};

/**
 * name속성으로 찾기
 * @param name
 * @returns {bigint | number | * | jQuery}
 */
$.fn.findByName = function(name) {
    return $(this).find("[name=" + name + "]");
};

/**
 * 길이 return
 * @returns {number}
 */
getLength = function(v) {
    var str = String(v).substring(0);
    if (str == null)
        return 0;

    // 개선된 FOR문으로 문자열 BYTE 계산
    var len = (function(s, b, i, c) {
        for (b = i = 0; c = s.charCodeAt(i++); b += c >> 11 ? 3 : c >> 7 ? 2 : 1);
        return b;
    })(str);

    return len;
};

