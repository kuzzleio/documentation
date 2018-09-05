$(document).ready(function() {

    app.initialize({
        version: "1",
        url: {
            base: "/"
        }
    });

    Prism.highlightAll();
});
! function(e, t) {
    for (var n in t) e[n] = t[n]
}(window, function(e) {
    var t = {};

    function n(r) {
        if (t[r]) return t[r].exports;
        var i = t[r] = {
            i: r,
            l: !1,
            exports: {}
        };
        return e[r].call(i.exports, i, i.exports, n), i.l = !0, i.exports
    }
    return n.m = e, n.c = t, n.d = function(e, t, r) {
        n.o(e, t) || Object.defineProperty(e, t, {
            configurable: !1,
            enumerable: !0,
            get: r
        })
    }, n.n = function(e) {
        var t = e && e.__esModule ? function() {
            return e.default
        } : function() {
            return e
        };
        return n.d(t, "a", t), t
    }, n.o = function(e, t) {
        return Object.prototype.hasOwnProperty.call(e, t)
    }, n.p = "", n(n.s = 6)
}([function(e, t, n) {
    "use strict";
    t.__esModule = !0, t.default = {
        createElement: function(e, t) {
            var n = document.createElement(e);
            t && Array.prototype.forEach.call(Object.keys(t), function(e) {
                n.setAttribute(e, t[e])
            });
            for (var r = arguments.length, i = Array(r > 2 ? r - 2 : 0), o = 2; o < r; o++) i[o - 2] = arguments[o];
            return function e(t) {
                Array.prototype.forEach.call(t, function(t) {
                    "string" == typeof t || "number" == typeof t ? n.textContent += t : Array.isArray(t) ? e(t) : void 0 !== t.__html ? n.innerHTML += t.__html : t instanceof Node && n.appendChild(t)
                })
            }(i), n
        }
    }, e.exports = t.default
}, function(e, t) {
    var n;
    n = function() {
        return this
    }();
    try {
        n = n || Function("return this")() || (0, eval)("this")
    } catch (e) {
        "object" == typeof window && (n = window)
    }
    e.exports = n
}, function(e, t, n) {
    "use strict";
    Object.defineProperty(t, "__esModule", {
        value: !0
    });
    var r = "function" == typeof fetch ? fetch.bind() : function(e, t) {
        return t = t || {}, new Promise(function(n, r) {
            var i = new XMLHttpRequest;
            i.open(t.method || "get", e);
            for (var o in t.headers) i.setRequestHeader(o, t.headers[o]);

            function a() {
                var e, t = [],
                    n = [],
                    r = {};
                return i.getAllResponseHeaders().replace(/^(.*?):\s*([\s\S]*?)$/gm, function(i, o, a) {
                    t.push(o = o.toLowerCase()), n.push([o, a]), e = r[o], r[o] = e ? e + "," + a : a
                }), {
                    ok: 1 == (i.status / 200 | 0),
                    status: i.status,
                    statusText: i.statusText,
                    url: i.responseURL,
                    clone: a,
                    text: function() {
                        return Promise.resolve(i.responseText)
                    },
                    json: function() {
                        return Promise.resolve(i.responseText).then(JSON.parse)
                    },
                    blob: function() {
                        return Promise.resolve(new Blob([i.response]))
                    },
                    headers: {
                        keys: function() {
                            return t
                        },
                        entries: function() {
                            return n
                        },
                        get: function(e) {
                            return r[e.toLowerCase()]
                        },
                        has: function(e) {
                            return e.toLowerCase() in r
                        }
                    }
                }
            }
            i.withCredentials = "include" == t.credentials, i.onload = function() {
                n(a())
            }, i.onerror = r, i.send(t.body)
        })
    };
    t.default = r
}, function(e, t, n) {
    "use strict";
    t.__esModule = !0;
    var r = function() {
        function e(t, n, r) {
            var i = this;
            ! function(e, t) {
                if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
            }(this, e), this.els_ = Array.prototype.slice.call("string" == typeof t ? document.querySelectorAll(t) : [].concat(t)), this.handler_ = "function" == typeof r ? {
                update: r
            } : r, this.events_ = [].concat(n), this.update_ = function(e) {
                return i.handler_.update(e)
            }
        }
        return e.prototype.listen = function() {
            var e = this;
            this.els_.forEach(function(t) {
                e.events_.forEach(function(n) {
                    t.addEventListener(n, e.update_, !1)
                })
            }), "function" == typeof this.handler_.setup && this.handler_.setup()
        }, e.prototype.unlisten = function() {
            var e = this;
            this.els_.forEach(function(t) {
                e.events_.forEach(function(n) {
                    t.removeEventListener(n, e.update_)
                })
            }), "function" == typeof this.handler_.reset && this.handler_.reset()
        }, e
    }();
    t.default = r
}, , , function(e, t, n) {
    "use strict";
    (function(e) {
        t.__esModule = !0, t.app = void 0, n(7), n(8), n(9), n(10), n(11), n(12), n(13);
        var r = s(n(14)),
            i = s(n(18)),
            o = s(n(19)),
            a = s(n(20));

        function s(e) {
            return e && e.__esModule ? e : {
                default: e
            }
        }
        window.Promise = window.Promise || r.default;
        var c = function(e) {
            return "en"
        };
        var l = {
            initialize: function(t) {
                new a.default.Event.Listener(document, "DOMContentLoaded", function() {
                    if (!(document.body instanceof HTMLElement)) throw new ReferenceError;
                    o.default.attach(document.body), Modernizr.addTest("ios", function() {
                        return !!navigator.userAgent.match(/(iPad|iPhone|iPod)/g)
                    });
                    var t = document.querySelectorAll("table:not([class])");
                    if (Array.prototype.forEach.call(t, function(t) {
                            var n = e.createElement("div", {
                                class: "md-typeset__scrollwrap"
                            }, e.createElement("div", {
                                class: "md-typeset__table"
                            }));
                            t.nextSibling ? t.parentNode.insertBefore(n, t.nextSibling) : t.parentNode.appendChild(n), n.children[0].appendChild(t)
                        }), i.default.isSupported()) {
                        var n = document.querySelectorAll(".codehilite > pre, pre > code");
                        Array.prototype.forEach.call(n, function(t, n) {
                            var r = "__code_" + n,
                                i = e.createElement("button", {
                                    class: "md-clipboard",
                                    title: c("clipboard.copy"),
                                    "data-clipboard-target": "#" + r + " pre, #" + r + " code"
                                }, e.createElement("span", {
                                    class: "md-clipboard__message"
                                })),
                                o = t.parentNode;
                            o.id = r, o.insertBefore(i, t)
                        }), new i.default(".md-clipboard").on("success", function(e) {
                            var t = e.trigger.querySelector(".md-clipboard__message");
                            if (!(t instanceof HTMLElement)) throw new ReferenceError;
                            e.clearSelection(), t.dataset.mdTimer && clearTimeout(parseInt(t.dataset.mdTimer, 10)), t.classList.add("md-clipboard__message--active"), t.innerHTML = c("clipboard.copied"), t.dataset.mdTimer = setTimeout(function() {
                                t.classList.remove("md-clipboard__message--active"), t.dataset.mdTimer = ""
                            }, 2e3).toString()
                        })
                    }
                    if (!Modernizr.details) {
                        var r = document.querySelectorAll("details > summary");
                        Array.prototype.forEach.call(r, function(e) {
                            e.addEventListener("click", function(e) {
                                var t = e.target.parentNode;
                                t.hasAttribute("open") ? t.removeAttribute("open") : t.setAttribute("open", "")
                            })
                        })
                    }
                    var a = function() {
                        if (document.location.hash) {
                            var e = document.getElementById(document.location.hash.substring(1));
                            if (!e) return;
                            for (var t = e.parentNode; t && !(t instanceof HTMLDetailsElement);) t = t.parentNode;
                            if (t && !t.open) {
                                t.open = !0;
                                var n = location.hash;
                                location.hash = " ", location.hash = n
                            }
                        }
                    };
                    if (window.addEventListener("hashchange", a), a(), Modernizr.ios) {
                        var s = document.querySelectorAll("[data-md-scrollfix]");
                        Array.prototype.forEach.call(s, function(e) {
                            e.addEventListener("touchstart", function() {
                                var t = e.scrollTop;
                                0 === t ? e.scrollTop = 1 : t + e.offsetHeight === e.scrollHeight && (e.scrollTop = t - 1)
                            })
                        })
                    }
                }).listen(), new a.default.Event.Listener(window, ["scroll", "resize", "orientationchange"], new a.default.Header.Shadow("[data-md-component=container]", "[data-md-component=header]")).listen(), new a.default.Event.Listener(window, ["scroll", "resize", "orientationchange"], new a.default.Header.Title("[data-md-component=title]", ".md-typeset h1")).listen(), document.querySelector("[data-md-component=hero]") && new a.default.Event.Listener(window, ["scroll", "resize", "orientationchange"], new a.default.Tabs.Toggle("[data-md-component=hero]")).listen(), document.querySelector("[data-md-component=tabs]") && new a.default.Event.Listener(window, ["scroll", "resize", "orientationchange"], new a.default.Tabs.Toggle("[data-md-component=tabs]")).listen(), new a.default.Event.MatchMedia("(min-width: 1220px)", new a.default.Event.Listener(window, ["scroll", "resize", "orientationchange"], new a.default.Sidebar.Position("[data-md-component=navigation]", "[data-md-component=header]"))), document.querySelector("[data-md-component=toc]") && new a.default.Event.MatchMedia("(min-width: 960px)", new a.default.Event.Listener(window, ["scroll", "resize", "orientationchange"], new a.default.Sidebar.Position("[data-md-component=toc]", "[data-md-component=header]"))), new a.default.Event.MatchMedia("(min-width: 960px)", new a.default.Event.Listener(window, "scroll", new a.default.Nav.Blur("[data-md-component=toc] [href]")));
                var n = document.querySelectorAll("[data-md-component=collapsible]");
                Array.prototype.forEach.call(n, function(e) {
                        new a.default.Event.MatchMedia("(min-width: 1220px)", new a.default.Event.Listener(e.previousElementSibling, "click", new a.default.Nav.Collapse(e)))
                    }), new a.default.Event.MatchMedia("(max-width: 1219px)", new a.default.Event.Listener("[data-md-component=navigation] [data-md-toggle]", "change", new a.default.Nav.Scrolling("[data-md-component=navigation] nav"))), document.querySelector("[data-md-component=search]") && (new a.default.Event.MatchMedia("(max-width: 959px)", new a.default.Event.Listener("[data-md-toggle=search]", "change", new a.default.Search.Lock("[data-md-toggle=search]"))), new a.default.Event.Listener("[data-md-component=reset]", "click", function() {
                        setTimeout(function() {
                            var e = document.querySelector("[data-md-component=query]");
                            if (!(e instanceof HTMLInputElement)) throw new ReferenceError;
                            e.focus()
                        }, 10)
                    }).listen(), new a.default.Event.Listener("[data-md-toggle=search]", "change", function(e) {
                        setTimeout(function(e) {
                            if (!(e instanceof HTMLInputElement)) throw new ReferenceError;
                            if (e.checked) {
                                var t = document.querySelector("[data-md-component=query]");
                                if (!(t instanceof HTMLInputElement)) throw new ReferenceError;
                                t.focus()
                            }
                        }, 400, e.target)
                    }).listen(), new a.default.Event.MatchMedia("(min-width: 960px)", new a.default.Event.Listener("[data-md-component=query]", "focus", function() {
                        var e = document.querySelector("[data-md-toggle=search]");
                        if (!(e instanceof HTMLInputElement)) throw new ReferenceError;
                        e.checked || (e.checked = !0, e.dispatchEvent(new CustomEvent("change")))
                    })), new a.default.Event.Listener(window, "keydown", function(e) {
                        var t = document.querySelector("[data-md-toggle=search]");
                        if (!(t instanceof HTMLInputElement)) throw new ReferenceError;
                        var n = document.querySelector("[data-md-component=query]");
                        if (!(n instanceof HTMLInputElement)) throw new ReferenceError;
                        if (!e.metaKey && !e.ctrlKey)
                            if (t.checked) {
                                if (13 === e.keyCode) {
                                    if (n === document.activeElement) {
                                        e.preventDefault();
                                        var r = document.querySelector("[data-md-component=search] [href][data-md-state=active]");
                                        r instanceof HTMLLinkElement && (window.location = r.getAttribute("href"), t.checked = !1, t.dispatchEvent(new CustomEvent("change")), n.blur())
                                    }
                                } else if (9 === e.keyCode || 27 === e.keyCode) t.checked = !1, t.dispatchEvent(new CustomEvent("change")), n.blur();
                                else if (-1 !== [8, 37, 39].indexOf(e.keyCode)) n !== document.activeElement && n.focus();
                                else if (-1 !== [38, 40].indexOf(e.keyCode)) {
                                    var i = e.keyCode,
                                        o = Array.prototype.slice.call(document.querySelectorAll("[data-md-component=query], [data-md-component=search] [href]")),
                                        a = o.find(function(e) {
                                            if (!(e instanceof HTMLElement)) throw new ReferenceError;
                                            return "active" === e.dataset.mdState
                                        });
                                    a && (a.dataset.mdState = "");
                                    var s = Math.max(0, (o.indexOf(a) + o.length + (38 === i ? -1 : 1)) % o.length);
                                    return o[s] && (o[s].dataset.mdState = "active", o[s].focus()), e.preventDefault(), e.stopPropagation(), !1
                                }
                            } else document.activeElement && !document.activeElement.form && (70 !== e.keyCode && 83 !== e.keyCode || (n.focus(), e.preventDefault()))
                    }).listen(), new a.default.Event.Listener(window, "keypress", function() {
                        var e = document.querySelector("[data-md-toggle=search]");
                        if (!(e instanceof HTMLInputElement)) throw new ReferenceError;
                        if (e.checked) {
                            var t = document.querySelector("[data-md-component=query]");
                            if (!(t instanceof HTMLInputElement)) throw new ReferenceError;
                            t !== document.activeElement && t.focus()
                        }
                    }).listen()), new a.default.Event.Listener(document.body, "keydown", function(e) {
                        if (9 === e.keyCode) {
                            var t = document.querySelectorAll("[data-md-component=navigation] .md-nav__link[for]:not([tabindex])");
                            Array.prototype.forEach.call(t, function(e) {
                                e.offsetHeight && (e.tabIndex = 0)
                            })
                        }
                    }).listen(), new a.default.Event.Listener(document.body, "mousedown", function() {
                        var e = document.querySelectorAll("[data-md-component=navigation] .md-nav__link[tabindex]");
                        Array.prototype.forEach.call(e, function(e) {
                            e.removeAttribute("tabIndex")
                        })
                    }).listen(), document.body.addEventListener("click", function() {
                        "tabbing" === document.body.dataset.mdState && (document.body.dataset.mdState = "")
                    }), new a.default.Event.MatchMedia("(max-width: 959px)", new a.default.Event.Listener("[data-md-component=navigation] [href^='#']", "click", function() {
                        var e = document.querySelector("[data-md-toggle=drawer]");
                        if (!(e instanceof HTMLInputElement)) throw new ReferenceError;
                        e.checked && (e.checked = !1, e.dispatchEvent(new CustomEvent("change")))
                    })),
                    function() {
                        var e = document.querySelector("[data-md-source]");
                        if (!e) return r.default.resolve([]);
                        if (!(e instanceof HTMLAnchorElement)) throw new ReferenceError;
                        switch (e.dataset.mdSource) {
                            case "github":
                                return new a.default.Source.Adapter.GitHub(e).fetch();
                            default:
                                return r.default.resolve([])
                        }
                    }().then(function(e) {
                        var t = document.querySelectorAll("[data-md-source]");
                        Array.prototype.forEach.call(t, function(t) {
                            new a.default.Source.Repository(t).initialize(e)
                        })
                    })
            }
        };
        t.app = l
    }).call(t, n(0))
}, function(e, t, n) {
    e.exports = n.p + "assets/images/icons/bitbucket.4ebea66e.svg"
}, function(e, t, n) {
    e.exports = n.p + "assets/images/icons/github.a4034fb1.svg"
}, function(e, t, n) {
    e.exports = n.p + "assets/images/icons/gitlab.348cdb3a.svg"
}, function(e, t) {}, function(e, t) {}, function(e, t) {
    try {
        var n = new window.CustomEvent("test");
        if (n.preventDefault(), !0 !== n.defaultPrevented) throw new Error("Could not prevent default")
    } catch (e) {
        var r = function(e, t) {
            var n, r;
            return t = t || {
                bubbles: !1,
                cancelable: !1,
                detail: void 0
            }, (n = document.createEvent("CustomEvent")).initCustomEvent(e, t.bubbles, t.cancelable, t.detail), r = n.preventDefault, n.preventDefault = function() {
                r.call(this);
                try {
                    Object.defineProperty(this, "defaultPrevented", {
                        get: function() {
                            return !0
                        }
                    })
                } catch (e) {
                    this.defaultPrevented = !0
                }
            }, n
        };
        r.prototype = window.Event.prototype, window.CustomEvent = r
    }
}, function(e, t, n) {
    window.fetch || (window.fetch = n(2).default || n(2))
}, function(e, t, n) {
    "use strict";
    (function(t) {
        var n = setTimeout;

        function r() {}

        function i(e) {
            if (!(this instanceof i)) throw new TypeError("Promises must be constructed via new");
            if ("function" != typeof e) throw new TypeError("not a function");
            this._state = 0, this._handled = !1, this._value = void 0, this._deferreds = [], l(e, this)
        }

        function o(e, t) {
            for (; 3 === e._state;) e = e._value;
            0 !== e._state ? (e._handled = !0, i._immediateFn(function() {
                var n = 1 === e._state ? t.onFulfilled : t.onRejected;
                if (null !== n) {
                    var r;
                    try {
                        r = n(e._value)
                    } catch (e) {
                        return void s(t.promise, e)
                    }
                    a(t.promise, r)
                } else(1 === e._state ? a : s)(t.promise, e._value)
            })) : e._deferreds.push(t)
        }

        function a(e, t) {
            try {
                if (t === e) throw new TypeError("A promise cannot be resolved with itself.");
                if (t && ("object" == typeof t || "function" == typeof t)) {
                    var n = t.then;
                    if (t instanceof i) return e._state = 3, e._value = t, void c(e);
                    if ("function" == typeof n) return void l((r = n, o = t, function() {
                        r.apply(o, arguments)
                    }), e)
                }
                e._state = 1, e._value = t, c(e)
            } catch (t) {
                s(e, t)
            }
            var r, o
        }

        function s(e, t) {
            e._state = 2, e._value = t, c(e)
        }

        function c(e) {
            2 === e._state && 0 === e._deferreds.length && i._immediateFn(function() {
                e._handled || i._unhandledRejectionFn(e._value)
            });
            for (var t = 0, n = e._deferreds.length; t < n; t++) o(e, e._deferreds[t]);
            e._deferreds = null
        }

        function l(e, t) {
            var n = !1;
            try {
                e(function(e) {
                    n || (n = !0, a(t, e))
                }, function(e) {
                    n || (n = !0, s(t, e))
                })
            } catch (e) {
                if (n) return;
                n = !0, s(t, e)
            }
        }
        i.prototype.catch = function(e) {
            return this.then(null, e)
        }, i.prototype.then = function(e, t) {
            var n = new this.constructor(r);
            return o(this, new function(e, t, n) {
                this.onFulfilled = "function" == typeof e ? e : null, this.onRejected = "function" == typeof t ? t : null, this.promise = n
            }(e, t, n)), n
        }, i.prototype.finally = function(e) {
            var t = this.constructor;
            return this.then(function(n) {
                return t.resolve(e()).then(function() {
                    return n
                })
            }, function(n) {
                return t.resolve(e()).then(function() {
                    return t.reject(n)
                })
            })
        }, i.all = function(e) {
            return new i(function(t, n) {
                if (!e || void 0 === e.length) throw new TypeError("Promise.all accepts an array");
                var r = Array.prototype.slice.call(e);
                if (0 === r.length) return t([]);
                var i = r.length;

                function o(e, a) {
                    try {
                        if (a && ("object" == typeof a || "function" == typeof a)) {
                            var s = a.then;
                            if ("function" == typeof s) return void s.call(a, function(t) {
                                o(e, t)
                            }, n)
                        }
                        r[e] = a, 0 == --i && t(r)
                    } catch (e) {
                        n(e)
                    }
                }
                for (var a = 0; a < r.length; a++) o(a, r[a])
            })
        }, i.resolve = function(e) {
            return e && "object" == typeof e && e.constructor === i ? e : new i(function(t) {
                t(e)
            })
        }, i.reject = function(e) {
            return new i(function(t, n) {
                n(e)
            })
        }, i.race = function(e) {
            return new i(function(t, n) {
                for (var r = 0, i = e.length; r < i; r++) e[r].then(t, n)
            })
        }, i._immediateFn = "function" == typeof t && function(e) {
            t(e)
        } || function(e) {
            n(e, 0)
        }, i._unhandledRejectionFn = function(e) {
            "undefined" != typeof console && console && console.warn("Possible Unhandled Promise Rejection:", e)
        }, e.exports = i
    }).call(t, n(15).setImmediate)
}, function(e, t, n) {
    (function(e) {
        var r = Function.prototype.apply;

        function i(e, t) {
            this._id = e, this._clearFn = t
        }
        t.setTimeout = function() {
            return new i(r.call(setTimeout, window, arguments), clearTimeout)
        }, t.setInterval = function() {
            return new i(r.call(setInterval, window, arguments), clearInterval)
        }, t.clearTimeout = t.clearInterval = function(e) {
            e && e.close()
        }, i.prototype.unref = i.prototype.ref = function() {}, i.prototype.close = function() {
            this._clearFn.call(window, this._id)
        }, t.enroll = function(e, t) {
            clearTimeout(e._idleTimeoutId), e._idleTimeout = t
        }, t.unenroll = function(e) {
            clearTimeout(e._idleTimeoutId), e._idleTimeout = -1
        }, t._unrefActive = t.active = function(e) {
            clearTimeout(e._idleTimeoutId);
            var t = e._idleTimeout;
            t >= 0 && (e._idleTimeoutId = setTimeout(function() {
                e._onTimeout && e._onTimeout()
            }, t))
        }, n(16), t.setImmediate = "undefined" != typeof self && self.setImmediate || void 0 !== e && e.setImmediate || this && this.setImmediate, t.clearImmediate = "undefined" != typeof self && self.clearImmediate || void 0 !== e && e.clearImmediate || this && this.clearImmediate
    }).call(t, n(1))
}, function(e, t, n) {
    (function(e, t) {
        ! function(e, n) {
            "use strict";
            if (!e.setImmediate) {
                var r, i, o, a, s, c = 1,
                    l = {},
                    u = !1,
                    d = e.document,
                    f = Object.getPrototypeOf && Object.getPrototypeOf(e);
                f = f && f.setTimeout ? f : e, "[object process]" === {}.toString.call(e.process) ? r = function(e) {
                    t.nextTick(function() {
                        p(e)
                    })
                } : ! function() {
                    if (e.postMessage && !e.importScripts) {
                        var t = !0,
                            n = e.onmessage;
                        return e.onmessage = function() {
                            t = !1
                        }, e.postMessage("", "*"), e.onmessage = n, t
                    }
                }() ? e.MessageChannel ? ((o = new MessageChannel).port1.onmessage = function(e) {
                    p(e.data)
                }, r = function(e) {
                    o.port2.postMessage(e)
                }) : d && "onreadystatechange" in d.createElement("script") ? (i = d.documentElement, r = function(e) {
                    var t = d.createElement("script");
                    t.onreadystatechange = function() {
                        p(e), t.onreadystatechange = null, i.removeChild(t), t = null
                    }, i.appendChild(t)
                }) : r = function(e) {
                    setTimeout(p, 0, e)
                } : (a = "setImmediate$" + Math.random() + "$", s = function(t) {
                    t.source === e && "string" == typeof t.data && 0 === t.data.indexOf(a) && p(+t.data.slice(a.length))
                }, e.addEventListener ? e.addEventListener("message", s, !1) : e.attachEvent("onmessage", s), r = function(t) {
                    e.postMessage(a + t, "*")
                }), f.setImmediate = function(e) {
                    "function" != typeof e && (e = new Function("" + e));
                    for (var t = new Array(arguments.length - 1), n = 0; n < t.length; n++) t[n] = arguments[n + 1];
                    var i = {
                        callback: e,
                        args: t
                    };
                    return l[c] = i, r(c), c++
                }, f.clearImmediate = h
            }

            function h(e) {
                delete l[e]
            }

            function p(e) {
                if (u) setTimeout(p, 0, e);
                else {
                    var t = l[e];
                    if (t) {
                        u = !0;
                        try {
                            ! function(e) {
                                var t = e.callback,
                                    r = e.args;
                                switch (r.length) {
                                    case 0:
                                        t();
                                        break;
                                    case 1:
                                        t(r[0]);
                                        break;
                                    case 2:
                                        t(r[0], r[1]);
                                        break;
                                    case 3:
                                        t(r[0], r[1], r[2]);
                                        break;
                                    default:
                                        t.apply(n, r)
                                }
                            }(t)
                        } finally {
                            h(e), u = !1
                        }
                    }
                }
            }
        }("undefined" == typeof self ? void 0 === e ? this : e : self)
    }).call(t, n(1), n(17))
}, function(e, t) {
    var n, r, i = e.exports = {};

    function o() {
        throw new Error("setTimeout has not been defined")
    }

    function a() {
        throw new Error("clearTimeout has not been defined")
    }

    function s(e) {
        if (n === setTimeout) return setTimeout(e, 0);
        if ((n === o || !n) && setTimeout) return n = setTimeout, setTimeout(e, 0);
        try {
            return n(e, 0)
        } catch (t) {
            try {
                return n.call(null, e, 0)
            } catch (t) {
                return n.call(this, e, 0)
            }
        }
    }! function() {
        try {
            n = "function" == typeof setTimeout ? setTimeout : o
        } catch (e) {
            n = o
        }
        try {
            r = "function" == typeof clearTimeout ? clearTimeout : a
        } catch (e) {
            r = a
        }
    }();
    var c, l = [],
        u = !1,
        d = -1;

    function f() {
        u && c && (u = !1, c.length ? l = c.concat(l) : d = -1, l.length && h())
    }

    function h() {
        if (!u) {
            var e = s(f);
            u = !0;
            for (var t = l.length; t;) {
                for (c = l, l = []; ++d < t;) c && c[d].run();
                d = -1, t = l.length
            }
            c = null, u = !1,
                function(e) {
                    if (r === clearTimeout) return clearTimeout(e);
                    if ((r === a || !r) && clearTimeout) return r = clearTimeout, clearTimeout(e);
                    try {
                        r(e)
                    } catch (t) {
                        try {
                            return r.call(null, e)
                        } catch (t) {
                            return r.call(this, e)
                        }
                    }
                }(e)
        }
    }

    function p(e, t) {
        this.fun = e, this.array = t
    }

    function m() {}
    i.nextTick = function(e) {
        var t = new Array(arguments.length - 1);
        if (arguments.length > 1)
            for (var n = 1; n < arguments.length; n++) t[n - 1] = arguments[n];
        l.push(new p(e, t)), 1 !== l.length || u || s(h)
    }, p.prototype.run = function() {
        this.fun.apply(null, this.array)
    }, i.title = "browser", i.browser = !0, i.env = {}, i.argv = [], i.version = "", i.versions = {}, i.on = m, i.addListener = m, i.once = m, i.off = m, i.removeListener = m, i.removeAllListeners = m, i.emit = m, i.prependListener = m, i.prependOnceListener = m, i.listeners = function(e) {
        return []
    }, i.binding = function(e) {
        throw new Error("process.binding is not supported")
    }, i.cwd = function() {
        return "/"
    }, i.chdir = function(e) {
        throw new Error("process.chdir is not supported")
    }, i.umask = function() {
        return 0
    }
}, function(e, t, n) {
    var r;
    r = function() {
        return function(e) {
            var t = {};

            function n(r) {
                if (t[r]) return t[r].exports;
                var i = t[r] = {
                    i: r,
                    l: !1,
                    exports: {}
                };
                return e[r].call(i.exports, i, i.exports, n), i.l = !0, i.exports
            }
            return n.m = e, n.c = t, n.i = function(e) {
                return e
            }, n.d = function(e, t, r) {
                n.o(e, t) || Object.defineProperty(e, t, {
                    configurable: !1,
                    enumerable: !0,
                    get: r
                })
            }, n.n = function(e) {
                var t = e && e.__esModule ? function() {
                    return e.default
                } : function() {
                    return e
                };
                return n.d(t, "a", t), t
            }, n.o = function(e, t) {
                return Object.prototype.hasOwnProperty.call(e, t)
            }, n.p = "", n(n.s = 3)
        }([function(e, t, n) {
            var r, i, o, a;
            a = function(e, t) {
                "use strict";
                var n, r = (n = t) && n.__esModule ? n : {
                    default: n
                };
                var i = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function(e) {
                    return typeof e
                } : function(e) {
                    return e && "function" == typeof Symbol && e.constructor === Symbol && e !== Symbol.prototype ? "symbol" : typeof e
                };
                var o = function() {
                        function e(e, t) {
                            for (var n = 0; n < t.length; n++) {
                                var r = t[n];
                                r.enumerable = r.enumerable || !1, r.configurable = !0, "value" in r && (r.writable = !0), Object.defineProperty(e, r.key, r)
                            }
                        }
                        return function(t, n, r) {
                            return n && e(t.prototype, n), r && e(t, r), t
                        }
                    }(),
                    a = function() {
                        function e(t) {
                            ! function(e, t) {
                                if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
                            }(this, e), this.resolveOptions(t), this.initSelection()
                        }
                        return o(e, [{
                            key: "resolveOptions",
                            value: function() {
                                var e = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : {};
                                this.action = e.action, this.container = e.container, this.emitter = e.emitter, this.target = e.target, this.text = e.text, this.trigger = e.trigger, this.selectedText = ""
                            }
                        }, {
                            key: "initSelection",
                            value: function() {
                                this.text ? this.selectFake() : this.target && this.selectTarget()
                            }
                        }, {
                            key: "selectFake",
                            value: function() {
                                var e = this,
                                    t = "rtl" == document.documentElement.getAttribute("dir");
                                this.removeFake(), this.fakeHandlerCallback = function() {
                                    return e.removeFake()
                                }, this.fakeHandler = this.container.addEventListener("click", this.fakeHandlerCallback) || !0, this.fakeElem = document.createElement("textarea"), this.fakeElem.style.fontSize = "12pt", this.fakeElem.style.border = "0", this.fakeElem.style.padding = "0", this.fakeElem.style.margin = "0", this.fakeElem.style.position = "absolute", this.fakeElem.style[t ? "right" : "left"] = "-9999px";
                                var n = window.pageYOffset || document.documentElement.scrollTop;
                                this.fakeElem.style.top = n + "px", this.fakeElem.setAttribute("readonly", ""), this.fakeElem.value = this.text, this.container.appendChild(this.fakeElem), this.selectedText = (0, r.default)(this.fakeElem), this.copyText()
                            }
                        }, {
                            key: "removeFake",
                            value: function() {
                                this.fakeHandler && (this.container.removeEventListener("click", this.fakeHandlerCallback), this.fakeHandler = null, this.fakeHandlerCallback = null), this.fakeElem && (this.container.removeChild(this.fakeElem), this.fakeElem = null)
                            }
                        }, {
                            key: "selectTarget",
                            value: function() {
                                this.selectedText = (0, r.default)(this.target), this.copyText()
                            }
                        }, {
                            key: "copyText",
                            value: function() {
                                var e = void 0;
                                try {
                                    e = document.execCommand(this.action)
                                } catch (t) {
                                    e = !1
                                }
                                this.handleResult(e)
                            }
                        }, {
                            key: "handleResult",
                            value: function(e) {
                                this.emitter.emit(e ? "success" : "error", {
                                    action: this.action,
                                    text: this.selectedText,
                                    trigger: this.trigger,
                                    clearSelection: this.clearSelection.bind(this)
                                })
                            }
                        }, {
                            key: "clearSelection",
                            value: function() {
                                this.trigger && this.trigger.focus(), window.getSelection().removeAllRanges()
                            }
                        }, {
                            key: "destroy",
                            value: function() {
                                this.removeFake()
                            }
                        }, {
                            key: "action",
                            set: function() {
                                var e = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : "copy";
                                if (this._action = e, "copy" !== this._action && "cut" !== this._action) throw new Error('Invalid "action" value, use either "copy" or "cut"')
                            },
                            get: function() {
                                return this._action
                            }
                        }, {
                            key: "target",
                            set: function(e) {
                                if (void 0 !== e) {
                                    if (!e || "object" !== (void 0 === e ? "undefined" : i(e)) || 1 !== e.nodeType) throw new Error('Invalid "target" value, use a valid Element');
                                    if ("copy" === this.action && e.hasAttribute("disabled")) throw new Error('Invalid "target" attribute. Please use "readonly" instead of "disabled" attribute');
                                    if ("cut" === this.action && (e.hasAttribute("readonly") || e.hasAttribute("disabled"))) throw new Error('Invalid "target" attribute. You can\'t cut text from elements with "readonly" or "disabled" attributes');
                                    this._target = e
                                }
                            },
                            get: function() {
                                return this._target
                            }
                        }]), e
                    }();
                e.exports = a
            }, i = [e, n(7)], void 0 === (o = "function" == typeof(r = a) ? r.apply(t, i) : r) || (e.exports = o)
        }, function(e, t, n) {
            var r = n(6),
                i = n(5);
            e.exports = function(e, t, n) {
                if (!e && !t && !n) throw new Error("Missing required arguments");
                if (!r.string(t)) throw new TypeError("Second argument must be a String");
                if (!r.fn(n)) throw new TypeError("Third argument must be a Function");
                if (r.node(e)) return f = t, h = n, (d = e).addEventListener(f, h), {
                    destroy: function() {
                        d.removeEventListener(f, h)
                    }
                };
                if (r.nodeList(e)) return c = e, l = t, u = n, Array.prototype.forEach.call(c, function(e) {
                    e.addEventListener(l, u)
                }), {
                    destroy: function() {
                        Array.prototype.forEach.call(c, function(e) {
                            e.removeEventListener(l, u)
                        })
                    }
                };
                if (r.string(e)) return o = e, a = t, s = n, i(document.body, o, a, s);
                throw new TypeError("First argument must be a String, HTMLElement, HTMLCollection, or NodeList");
                var o, a, s, c, l, u, d, f, h
            }
        }, function(e, t) {
            function n() {}
            n.prototype = {
                on: function(e, t, n) {
                    var r = this.e || (this.e = {});
                    return (r[e] || (r[e] = [])).push({
                        fn: t,
                        ctx: n
                    }), this
                },
                once: function(e, t, n) {
                    var r = this;

                    function i() {
                        r.off(e, i), t.apply(n, arguments)
                    }
                    return i._ = t, this.on(e, i, n)
                },
                emit: function(e) {
                    for (var t = [].slice.call(arguments, 1), n = ((this.e || (this.e = {}))[e] || []).slice(), r = 0, i = n.length; r < i; r++) n[r].fn.apply(n[r].ctx, t);
                    return this
                },
                off: function(e, t) {
                    var n = this.e || (this.e = {}),
                        r = n[e],
                        i = [];
                    if (r && t)
                        for (var o = 0, a = r.length; o < a; o++) r[o].fn !== t && r[o].fn._ !== t && i.push(r[o]);
                    return i.length ? n[e] = i : delete n[e], this
                }
            }, e.exports = n
        }, function(e, t, n) {
            var r, i, o, a;
            a = function(e, t, n, r) {
                "use strict";
                var i = s(t),
                    o = s(n),
                    a = s(r);

                function s(e) {
                    return e && e.__esModule ? e : {
                        default: e
                    }
                }
                var c = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function(e) {
                    return typeof e
                } : function(e) {
                    return e && "function" == typeof Symbol && e.constructor === Symbol && e !== Symbol.prototype ? "symbol" : typeof e
                };
                var l = function() {
                    function e(e, t) {
                        for (var n = 0; n < t.length; n++) {
                            var r = t[n];
                            r.enumerable = r.enumerable || !1, r.configurable = !0, "value" in r && (r.writable = !0), Object.defineProperty(e, r.key, r)
                        }
                    }
                    return function(t, n, r) {
                        return n && e(t.prototype, n), r && e(t, r), t
                    }
                }();
                var u = function(e) {
                    function t(e, n) {
                        ! function(e, t) {
                            if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
                        }(this, t);
                        var r = function(e, t) {
                            if (!e) throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
                            return !t || "object" != typeof t && "function" != typeof t ? e : t
                        }(this, (t.__proto__ || Object.getPrototypeOf(t)).call(this));
                        return r.resolveOptions(n), r.listenClick(e), r
                    }
                    return function(e, t) {
                        if ("function" != typeof t && null !== t) throw new TypeError("Super expression must either be null or a function, not " + typeof t);
                        e.prototype = Object.create(t && t.prototype, {
                            constructor: {
                                value: e,
                                enumerable: !1,
                                writable: !0,
                                configurable: !0
                            }
                        }), t && (Object.setPrototypeOf ? Object.setPrototypeOf(e, t) : e.__proto__ = t)
                    }(t, o.default), l(t, [{
                        key: "resolveOptions",
                        value: function() {
                            var e = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : {};
                            this.action = "function" == typeof e.action ? e.action : this.defaultAction, this.target = "function" == typeof e.target ? e.target : this.defaultTarget, this.text = "function" == typeof e.text ? e.text : this.defaultText, this.container = "object" === c(e.container) ? e.container : document.body
                        }
                    }, {
                        key: "listenClick",
                        value: function(e) {
                            var t = this;
                            this.listener = (0, a.default)(e, "click", function(e) {
                                return t.onClick(e)
                            })
                        }
                    }, {
                        key: "onClick",
                        value: function(e) {
                            var t = e.delegateTarget || e.currentTarget;
                            this.clipboardAction && (this.clipboardAction = null), this.clipboardAction = new i.default({
                                action: this.action(t),
                                target: this.target(t),
                                text: this.text(t),
                                container: this.container,
                                trigger: t,
                                emitter: this
                            })
                        }
                    }, {
                        key: "defaultAction",
                        value: function(e) {
                            return d("action", e)
                        }
                    }, {
                        key: "defaultTarget",
                        value: function(e) {
                            var t = d("target", e);
                            if (t) return document.querySelector(t)
                        }
                    }, {
                        key: "defaultText",
                        value: function(e) {
                            return d("text", e)
                        }
                    }, {
                        key: "destroy",
                        value: function() {
                            this.listener.destroy(), this.clipboardAction && (this.clipboardAction.destroy(), this.clipboardAction = null)
                        }
                    }], [{
                        key: "isSupported",
                        value: function() {
                            var e = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : ["copy", "cut"],
                                t = "string" == typeof e ? [e] : e,
                                n = !!document.queryCommandSupported;
                            return t.forEach(function(e) {
                                n = n && !!document.queryCommandSupported(e)
                            }), n
                        }
                    }]), t
                }();

                function d(e, t) {
                    var n = "data-clipboard-" + e;
                    if (t.hasAttribute(n)) return t.getAttribute(n)
                }
                e.exports = u
            }, i = [e, n(0), n(2), n(1)], void 0 === (o = "function" == typeof(r = a) ? r.apply(t, i) : r) || (e.exports = o)
        }, function(e, t) {
            var n = 9;
            if ("undefined" != typeof Element && !Element.prototype.matches) {
                var r = Element.prototype;
                r.matches = r.matchesSelector || r.mozMatchesSelector || r.msMatchesSelector || r.oMatchesSelector || r.webkitMatchesSelector
            }
            e.exports = function(e, t) {
                for (; e && e.nodeType !== n;) {
                    if ("function" == typeof e.matches && e.matches(t)) return e;
                    e = e.parentNode
                }
            }
        }, function(e, t, n) {
            var r = n(4);

            function i(e, t, n, i, o) {
                var a = function(e, t, n, i) {
                    return function(n) {
                        n.delegateTarget = r(n.target, t), n.delegateTarget && i.call(e, n)
                    }
                }.apply(this, arguments);
                return e.addEventListener(n, a, o), {
                    destroy: function() {
                        e.removeEventListener(n, a, o)
                    }
                }
            }
            e.exports = function(e, t, n, r, o) {
                return "function" == typeof e.addEventListener ? i.apply(null, arguments) : "function" == typeof n ? i.bind(null, document).apply(null, arguments) : ("string" == typeof e && (e = document.querySelectorAll(e)), Array.prototype.map.call(e, function(e) {
                    return i(e, t, n, r, o)
                }))
            }
        }, function(e, t) {
            t.node = function(e) {
                return void 0 !== e && e instanceof HTMLElement && 1 === e.nodeType
            }, t.nodeList = function(e) {
                var n = Object.prototype.toString.call(e);
                return void 0 !== e && ("[object NodeList]" === n || "[object HTMLCollection]" === n) && "length" in e && (0 === e.length || t.node(e[0]))
            }, t.string = function(e) {
                return "string" == typeof e || e instanceof String
            }, t.fn = function(e) {
                return "[object Function]" === Object.prototype.toString.call(e)
            }
        }, function(e, t) {
            e.exports = function(e) {
                var t;
                if ("SELECT" === e.nodeName) e.focus(), t = e.value;
                else if ("INPUT" === e.nodeName || "TEXTAREA" === e.nodeName) {
                    var n = e.hasAttribute("readonly");
                    n || e.setAttribute("readonly", ""), e.select(), e.setSelectionRange(0, e.value.length), n || e.removeAttribute("readonly"), t = e.value
                } else {
                    e.hasAttribute("contenteditable") && e.focus();
                    var r = window.getSelection(),
                        i = document.createRange();
                    i.selectNodeContents(e), r.removeAllRanges(), r.addRange(i), t = r.toString()
                }
                return t
            }
        }])
    }, e.exports = r()
}, function(e, t, n) {
    var r;
    ! function() {
        "use strict";

        function i(e, t) {
            var n;
            if (t = t || {}, this.trackingClick = !1, this.trackingClickStart = 0, this.targetElement = null, this.touchStartX = 0, this.touchStartY = 0, this.lastTouchIdentifier = 0, this.touchBoundary = t.touchBoundary || 10, this.layer = e, this.tapDelay = t.tapDelay || 200, this.tapTimeout = t.tapTimeout || 700, !i.notNeeded(e)) {
                for (var r = ["onMouse", "onClick", "onTouchStart", "onTouchMove", "onTouchEnd", "onTouchCancel"], o = 0, s = r.length; o < s; o++) this[r[o]] = c(this[r[o]], this);
                a && (e.addEventListener("mouseover", this.onMouse, !0), e.addEventListener("mousedown", this.onMouse, !0), e.addEventListener("mouseup", this.onMouse, !0)), e.addEventListener("click", this.onClick, !0), e.addEventListener("touchstart", this.onTouchStart, !1), e.addEventListener("touchmove", this.onTouchMove, !1), e.addEventListener("touchend", this.onTouchEnd, !1), e.addEventListener("touchcancel", this.onTouchCancel, !1), Event.prototype.stopImmediatePropagation || (e.removeEventListener = function(t, n, r) {
                    var i = Node.prototype.removeEventListener;
                    "click" === t ? i.call(e, t, n.hijacked || n, r) : i.call(e, t, n, r)
                }, e.addEventListener = function(t, n, r) {
                    var i = Node.prototype.addEventListener;
                    "click" === t ? i.call(e, t, n.hijacked || (n.hijacked = function(e) {
                        e.propagationStopped || n(e)
                    }), r) : i.call(e, t, n, r)
                }), "function" == typeof e.onclick && (n = e.onclick, e.addEventListener("click", function(e) {
                    n(e)
                }, !1), e.onclick = null)
            }

            function c(e, t) {
                return function() {
                    return e.apply(t, arguments)
                }
            }
        }
        var o = navigator.userAgent.indexOf("Windows Phone") >= 0,
            a = navigator.userAgent.indexOf("Android") > 0 && !o,
            s = /iP(ad|hone|od)/.test(navigator.userAgent) && !o,
            c = s && /OS 4_\d(_\d)?/.test(navigator.userAgent),
            l = s && /OS [6-7]_\d/.test(navigator.userAgent),
            u = navigator.userAgent.indexOf("BB10") > 0;
        i.prototype.needsClick = function(e) {
            switch (e.nodeName.toLowerCase()) {
                case "button":
                case "select":
                case "textarea":
                    if (e.disabled) return !0;
                    break;
                case "input":
                    if (s && "file" === e.type || e.disabled) return !0;
                    break;
                case "label":
                case "iframe":
                case "video":
                    return !0
            }
            return /\bneedsclick\b/.test(e.className)
        }, i.prototype.needsFocus = function(e) {
            switch (e.nodeName.toLowerCase()) {
                case "textarea":
                    return !0;
                case "select":
                    return !a;
                case "input":
                    switch (e.type) {
                        case "button":
                        case "checkbox":
                        case "file":
                        case "image":
                        case "radio":
                        case "submit":
                            return !1
                    }
                    return !e.disabled && !e.readOnly;
                default:
                    return /\bneedsfocus\b/.test(e.className)
            }
        }, i.prototype.sendClick = function(e, t) {
            var n, r;
            document.activeElement && document.activeElement !== e && document.activeElement.blur(), r = t.changedTouches[0], (n = document.createEvent("MouseEvents")).initMouseEvent(this.determineEventType(e), !0, !0, window, 1, r.screenX, r.screenY, r.clientX, r.clientY, !1, !1, !1, !1, 0, null), n.forwardedTouchEvent = !0, e.dispatchEvent(n)
        }, i.prototype.determineEventType = function(e) {
            return a && "select" === e.tagName.toLowerCase() ? "mousedown" : "click"
        }, i.prototype.focus = function(e) {
            var t;
            s && e.setSelectionRange && 0 !== e.type.indexOf("date") && "time" !== e.type && "month" !== e.type ? (t = e.value.length, e.setSelectionRange(t, t)) : e.focus()
        }, i.prototype.updateScrollParent = function(e) {
            var t, n;
            if (!(t = e.fastClickScrollParent) || !t.contains(e)) {
                n = e;
                do {
                    if (n.scrollHeight > n.offsetHeight) {
                        t = n, e.fastClickScrollParent = n;
                        break
                    }
                    n = n.parentElement
                } while (n)
            }
            t && (t.fastClickLastScrollTop = t.scrollTop)
        }, i.prototype.getTargetElementFromEventTarget = function(e) {
            return e.nodeType === Node.TEXT_NODE ? e.parentNode : e
        }, i.prototype.onTouchStart = function(e) {
            var t, n, r;
            if (e.targetTouches.length > 1) return !0;
            if (t = this.getTargetElementFromEventTarget(e.target), n = e.targetTouches[0], s) {
                if ((r = window.getSelection()).rangeCount && !r.isCollapsed) return !0;
                if (!c) {
                    if (n.identifier && n.identifier === this.lastTouchIdentifier) return e.preventDefault(), !1;
                    this.lastTouchIdentifier = n.identifier, this.updateScrollParent(t)
                }
            }
            return this.trackingClick = !0, this.trackingClickStart = e.timeStamp, this.targetElement = t, this.touchStartX = n.pageX, this.touchStartY = n.pageY, e.timeStamp - this.lastClickTime < this.tapDelay && e.preventDefault(), !0
        }, i.prototype.touchHasMoved = function(e) {
            var t = e.changedTouches[0],
                n = this.touchBoundary;
            return Math.abs(t.pageX - this.touchStartX) > n || Math.abs(t.pageY - this.touchStartY) > n
        }, i.prototype.onTouchMove = function(e) {
            return !this.trackingClick || ((this.targetElement !== this.getTargetElementFromEventTarget(e.target) || this.touchHasMoved(e)) && (this.trackingClick = !1, this.targetElement = null), !0)
        }, i.prototype.findControl = function(e) {
            return void 0 !== e.control ? e.control : e.htmlFor ? document.getElementById(e.htmlFor) : e.querySelector("button, input:not([type=hidden]), keygen, meter, output, progress, select, textarea")
        }, i.prototype.onTouchEnd = function(e) {
            var t, n, r, i, o, u = this.targetElement;
            if (!this.trackingClick) return !0;
            if (e.timeStamp - this.lastClickTime < this.tapDelay) return this.cancelNextClick = !0, !0;
            if (e.timeStamp - this.trackingClickStart > this.tapTimeout) return !0;
            if (this.cancelNextClick = !1, this.lastClickTime = e.timeStamp, n = this.trackingClickStart, this.trackingClick = !1, this.trackingClickStart = 0, l && (o = e.changedTouches[0], (u = document.elementFromPoint(o.pageX - window.pageXOffset, o.pageY - window.pageYOffset) || u).fastClickScrollParent = this.targetElement.fastClickScrollParent), "label" === (r = u.tagName.toLowerCase())) {
                if (t = this.findControl(u)) {
                    if (this.focus(u), a) return !1;
                    u = t
                }
            } else if (this.needsFocus(u)) return e.timeStamp - n > 100 || s && window.top !== window && "input" === r ? (this.targetElement = null, !1) : (this.focus(u), this.sendClick(u, e), s && "select" === r || (this.targetElement = null, e.preventDefault()), !1);
            return !(!s || c || !(i = u.fastClickScrollParent) || i.fastClickLastScrollTop === i.scrollTop) || (this.needsClick(u) || (e.preventDefault(), this.sendClick(u, e)), !1)
        }, i.prototype.onTouchCancel = function() {
            this.trackingClick = !1, this.targetElement = null
        }, i.prototype.onMouse = function(e) {
            return !this.targetElement || (!!e.forwardedTouchEvent || (!e.cancelable || (!(!this.needsClick(this.targetElement) || this.cancelNextClick) || (e.stopImmediatePropagation ? e.stopImmediatePropagation() : e.propagationStopped = !0, e.stopPropagation(), e.preventDefault(), !1))))
        }, i.prototype.onClick = function(e) {
            var t;
            return this.trackingClick ? (this.targetElement = null, this.trackingClick = !1, !0) : "submit" === e.target.type && 0 === e.detail || ((t = this.onMouse(e)) || (this.targetElement = null), t)
        }, i.prototype.destroy = function() {
            var e = this.layer;
            a && (e.removeEventListener("mouseover", this.onMouse, !0), e.removeEventListener("mousedown", this.onMouse, !0), e.removeEventListener("mouseup", this.onMouse, !0)), e.removeEventListener("click", this.onClick, !0), e.removeEventListener("touchstart", this.onTouchStart, !1), e.removeEventListener("touchmove", this.onTouchMove, !1), e.removeEventListener("touchend", this.onTouchEnd, !1), e.removeEventListener("touchcancel", this.onTouchCancel, !1)
        }, i.notNeeded = function(e) {
            var t, n, r;
            if (void 0 === window.ontouchstart) return !0;
            if (n = +(/Chrome\/([0-9]+)/.exec(navigator.userAgent) || [, 0])[1]) {
                if (!a) return !0;
                if (t = document.querySelector("meta[name=viewport]")) {
                    if (-1 !== t.content.indexOf("user-scalable=no")) return !0;
                    if (n > 31 && document.documentElement.scrollWidth <= window.outerWidth) return !0
                }
            }
            if (u && (r = navigator.userAgent.match(/Version\/([0-9]*)\.([0-9]*)/))[1] >= 10 && r[2] >= 3 && (t = document.querySelector("meta[name=viewport]"))) {
                if (-1 !== t.content.indexOf("user-scalable=no")) return !0;
                if (document.documentElement.scrollWidth <= window.outerWidth) return !0
            }
            return "none" === e.style.msTouchAction || "manipulation" === e.style.touchAction || (!!(+(/Firefox\/([0-9]+)/.exec(navigator.userAgent) || [, 0])[1] >= 27 && (t = document.querySelector("meta[name=viewport]")) && (-1 !== t.content.indexOf("user-scalable=no") || document.documentElement.scrollWidth <= window.outerWidth)) || ("none" === e.style.touchAction || "manipulation" === e.style.touchAction))
        }, i.attach = function(e, t) {
            return new i(e, t)
        }, void 0 === (r = function() {
            return i
        }.call(t, n, t, e)) || (e.exports = r)
    }()
}, function(e, t, n) {
    "use strict";
    t.__esModule = !0;
    var r = u(n(21)),
        i = u(n(23)),
        o = u(n(26)),
        a = u(n(30)),
        s = u(n(36)),
        c = u(n(38)),
        l = u(n(44));

    function u(e) {
        return e && e.__esModule ? e : {
            default: e
        }
    }
    t.default = {
        Event: r.default,
        Header: i.default,
        Nav: o.default,
        Search: a.default,
        Sidebar: s.default,
        Source: c.default,
        Tabs: l.default
    }
}, function(e, t, n) {
    "use strict";
    t.__esModule = !0;
    var r = o(n(3)),
        i = o(n(22));

    function o(e) {
        return e && e.__esModule ? e : {
            default: e
        }
    }
    t.default = {
        Listener: r.default,
        MatchMedia: i.default
    }
}, function(e, t, n) {
    "use strict";
    t.__esModule = !0;
    var r, i = n(3);
    (r = i) && r.__esModule;
    t.default = function e(t, n) {
        ! function(e, t) {
            if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
        }(this, e), this.handler_ = function(e) {
            e.matches ? n.listen() : n.unlisten()
        };
        var r = window.matchMedia(t);
        r.addListener(this.handler_), this.handler_(r)
    }
}, function(e, t, n) {
    "use strict";
    t.__esModule = !0;
    var r = o(n(24)),
        i = o(n(25));

    function o(e) {
        return e && e.__esModule ? e : {
            default: e
        }
    }
    t.default = {
        Shadow: r.default,
        Title: i.default
    }
}, function(e, t, n) {
    "use strict";
    t.__esModule = !0;
    var r = function() {
        function e(t, n) {
            ! function(e, t) {
                if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
            }(this, e);
            var r = "string" == typeof t ? document.querySelector(t) : t;
            if (!(r instanceof HTMLElement && r.parentNode instanceof HTMLElement)) throw new ReferenceError;
            if (this.el_ = r.parentNode, !((r = "string" == typeof n ? document.querySelector(n) : n) instanceof HTMLElement)) throw new ReferenceError;
            this.header_ = r, this.height_ = 0, this.active_ = !1
        }
        return e.prototype.setup = function() {
            for (var e = this.el_; e = e.previousElementSibling;) {
                if (!(e instanceof HTMLElement)) throw new ReferenceError;
                this.height_ += e.offsetHeight
            }
            this.update()
        }, e.prototype.update = function(e) {
            if (!e || "resize" !== e.type && "orientationchange" !== e.type) {
                var t = window.pageYOffset >= this.height_;
                t !== this.active_ && (this.header_.dataset.mdState = (this.active_ = t) ? "shadow" : "")
            } else this.height_ = 0, this.setup()
        }, e.prototype.reset = function() {
            this.header_.dataset.mdState = "", this.height_ = 0, this.active_ = !1
        }, e
    }();
    t.default = r
}, function(e, t, n) {
    "use strict";
    t.__esModule = !0;
    var r = function() {
        function e(t, n) {
            ! function(e, t) {
                if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
            }(this, e);
            var r = "string" == typeof t ? document.querySelector(t) : t;
            if (!(r instanceof HTMLElement)) throw new ReferenceError;
            if (this.el_ = r, !((r = "string" == typeof n ? document.querySelector(n) : n) instanceof HTMLHeadingElement)) throw new ReferenceError;
            this.header_ = r, this.active_ = !1
        }
        return e.prototype.setup = function() {
            var e = this;
            Array.prototype.forEach.call(this.el_.children, function(t) {
                t.style.width = e.el_.offsetWidth - 20 + "px"
            })
        }, e.prototype.update = function(e) {
            var t = this,
                n = window.pageYOffset >= this.header_.offsetTop;
            n !== this.active_ && (this.el_.dataset.mdState = (this.active_ = n) ? "active" : ""), "resize" !== e.type && "orientationchange" !== e.type || Array.prototype.forEach.call(this.el_.children, function(e) {
                e.style.width = t.el_.offsetWidth - 20 + "px"
            })
        }, e.prototype.reset = function() {
            this.el_.dataset.mdState = "", this.el_.style.width = "", this.active_ = !1
        }, e
    }();
    t.default = r
}, function(e, t, n) {
    "use strict";
    t.__esModule = !0;
    var r = a(n(27)),
        i = a(n(28)),
        o = a(n(29));

    function a(e) {
        return e && e.__esModule ? e : {
            default: e
        }
    }
    t.default = {
        Blur: r.default,
        Collapse: i.default,
        Scrolling: o.default
    }
}, function(e, t, n) {
    "use strict";
    t.__esModule = !0;
    var r = function() {
        function e(t) {
            ! function(e, t) {
                if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
            }(this, e), this.els_ = "string" == typeof t ? document.querySelectorAll(t) : t, this.index_ = 0, this.offset_ = window.pageYOffset, this.dir_ = !1, this.anchors_ = [].reduce.call(this.els_, function(e, t) {
                return e.concat(document.getElementById(t.hash.substring(1)) || [])
            }, [])
        }
        return e.prototype.setup = function() {
            this.update()
        }, e.prototype.update = function() {
            var e = window.pageYOffset,
                t = this.offset_ - e < 0;
            if (this.dir_ !== t && (this.index_ = this.index_ = t ? 0 : this.els_.length - 1), 0 !== this.anchors_.length) {
                if (this.offset_ <= e)
                    for (var n = this.index_ + 1; n < this.els_.length && this.anchors_[n].offsetTop - 80 <= e; n++) n > 0 && (this.els_[n - 1].dataset.mdState = "blur"), this.index_ = n;
                else
                    for (var r = this.index_; r >= 0; r--) {
                        if (!(this.anchors_[r].offsetTop - 80 > e)) {
                            this.index_ = r;
                            break
                        }
                        r > 0 && (this.els_[r - 1].dataset.mdState = "")
                    }
                this.offset_ = e, this.dir_ = t
            }
        }, e.prototype.reset = function() {
            Array.prototype.forEach.call(this.els_, function(e) {
                e.dataset.mdState = ""
            }), this.index_ = 0, this.offset_ = window.pageYOffset
        }, e
    }();
    t.default = r
}, function(e, t, n) {
    "use strict";
    t.__esModule = !0;
    var r = function() {
        function e(t) {
            ! function(e, t) {
                if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
            }(this, e);
            var n = "string" == typeof t ? document.querySelector(t) : t;
            if (!(n instanceof HTMLElement)) throw new ReferenceError;
            this.el_ = n
        }
        return e.prototype.setup = function() {
            var e = this.el_.getBoundingClientRect().height;
            this.el_.style.display = e ? "block" : "none", this.el_.style.overflow = e ? "visible" : "hidden"
        }, e.prototype.update = function() {
            var e = this,
                t = this.el_.getBoundingClientRect().height;
            if (this.el_.style.display = "block", this.el_.style.overflow = "", t) this.el_.style.maxHeight = t + "px", requestAnimationFrame(function() {
                e.el_.setAttribute("data-md-state", "animate"), e.el_.style.maxHeight = "0px"
            });
            else {
                this.el_.setAttribute("data-md-state", "expand"), this.el_.style.maxHeight = "";
                var n = this.el_.getBoundingClientRect().height;
                this.el_.removeAttribute("data-md-state"), this.el_.style.maxHeight = "0px", requestAnimationFrame(function() {
                    e.el_.setAttribute("data-md-state", "animate"), e.el_.style.maxHeight = n + "px"
                })
            }
            this.el_.addEventListener("transitionend", function e(n) {
                var r = n.target;
                if (!(r instanceof HTMLElement)) throw new ReferenceError;
                r.removeAttribute("data-md-state"), r.style.maxHeight = "", r.style.display = t ? "none" : "block", r.style.overflow = t ? "hidden" : "visible", r.removeEventListener("transitionend", e)
            }, !1)
        }, e.prototype.reset = function() {
            this.el_.dataset.mdState = "", this.el_.style.maxHeight = "", this.el_.style.display = "", this.el_.style.overflow = ""
        }, e
    }();
    t.default = r
}, function(e, t, n) {
    "use strict";
    t.__esModule = !0;
    var r = function() {
        function e(t) {
            ! function(e, t) {
                if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
            }(this, e);
            var n = "string" == typeof t ? document.querySelector(t) : t;
            if (!(n instanceof HTMLElement)) throw new ReferenceError;
            this.el_ = n
        }
        return e.prototype.setup = function() {
            this.el_.children[this.el_.children.length - 1].style.webkitOverflowScrolling = "touch";
            var e = this.el_.querySelectorAll("[data-md-toggle]");
            Array.prototype.forEach.call(e, function(e) {
                if (!(e instanceof HTMLInputElement)) throw new ReferenceError;
                if (e.checked) {
                    var t = e.nextElementSibling;
                    if (!(t instanceof HTMLElement)) throw new ReferenceError;
                    for (;
                        "NAV" !== t.tagName && t.nextElementSibling;) t = t.nextElementSibling;
                    if (!(e.parentNode instanceof HTMLElement && e.parentNode.parentNode instanceof HTMLElement)) throw new ReferenceError;
                    var n = e.parentNode.parentNode,
                        r = t.children[t.children.length - 1];
                    n.style.webkitOverflowScrolling = "", r.style.webkitOverflowScrolling = "touch"
                }
            })
        }, e.prototype.update = function(e) {
            var t = e.target;
            if (!(t instanceof HTMLElement)) throw new ReferenceError;
            var n = t.nextElementSibling;
            if (!(n instanceof HTMLElement)) throw new ReferenceError;
            for (;
                "NAV" !== n.tagName && n.nextElementSibling;) n = n.nextElementSibling;
            if (!(t.parentNode instanceof HTMLElement && t.parentNode.parentNode instanceof HTMLElement)) throw new ReferenceError;
            var r = t.parentNode.parentNode,
                i = n.children[n.children.length - 1];
            if (r.style.webkitOverflowScrolling = "", i.style.webkitOverflowScrolling = "", !t.checked) {
                n.addEventListener("transitionend", function e() {
                    n instanceof HTMLElement && (r.style.webkitOverflowScrolling = "touch", n.removeEventListener("transitionend", e))
                }, !1)
            }
            if (t.checked) {
                n.addEventListener("transitionend", function e() {
                    n instanceof HTMLElement && (i.style.webkitOverflowScrolling = "touch", n.removeEventListener("transitionend", e))
                }, !1)
            }
        }, e.prototype.reset = function() {
            this.el_.children[1].style.webkitOverflowScrolling = "";
            var e = this.el_.querySelectorAll("[data-md-toggle]");
            Array.prototype.forEach.call(e, function(e) {
                if (!(e instanceof HTMLInputElement)) throw new ReferenceError;
                if (e.checked) {
                    var t = e.nextElementSibling;
                    if (!(t instanceof HTMLElement)) throw new ReferenceError;
                    for (;
                        "NAV" !== t.tagName && t.nextElementSibling;) t = t.nextElementSibling;
                    if (!(e.parentNode instanceof HTMLElement && e.parentNode.parentNode instanceof HTMLElement)) throw new ReferenceError;
                    var n = e.parentNode.parentNode,
                        r = t.children[t.children.length - 1];
                    n.style.webkitOverflowScrolling = "", r.style.webkitOverflowScrolling = ""
                }
            })
        }, e
    }();
    t.default = r
}, function(e, t, n) {
    "use strict";
    t.__esModule = !0;
    var r = o(n(31)),
        i = o(n(32));

    function o(e) {
        return e && e.__esModule ? e : {
            default: e
        }
    }
    t.default = {
        Lock: r.default,
        Result: i.default
    }
}, function(e, t, n) {
    "use strict";
    t.__esModule = !0;
    var r = function() {
        function e(t) {
            ! function(e, t) {
                if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
            }(this, e);
            var n = "string" == typeof t ? document.querySelector(t) : t;
            if (!(n instanceof HTMLInputElement)) throw new ReferenceError;
            if (this.el_ = n, !document.body) throw new ReferenceError;
            this.lock_ = document.body
        }
        return e.prototype.setup = function() {
            this.update()
        }, e.prototype.update = function() {
            var e = this;
            this.el_.checked ? (this.offset_ = window.pageYOffset, setTimeout(function() {
                window.scrollTo(0, 0), e.el_.checked && (e.lock_.dataset.mdState = "lock")
            }, 400)) : (this.lock_.dataset.mdState = "", setTimeout(function() {
                void 0 !== e.offset_ && window.scrollTo(0, e.offset_)
            }, 100))
        }, e.prototype.reset = function() {
            "lock" === this.lock_.dataset.mdState && window.scrollTo(0, this.offset_), this.lock_.dataset.mdState = ""
        }, e
    }();
    t.default = r
}, function(e, t, n) {
    "use strict";
    (function(e) {
        t.__esModule = !0;
        var r = o(n(33)),
            i = o(n(34));

        function o(e) {
            return e && e.__esModule ? e : {
                default: e
            }
        }
        var a = function(e) {
                return "en"
            },
            s = function() {
                function t(e, n) {
                    ! function(e, t) {
                        if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
                    }(this, t);
                    var r = "string" == typeof e ? document.querySelector(e) : e;
                    if (!(r instanceof HTMLElement)) throw new ReferenceError;
                    this.el_ = r;
                    var o = Array.prototype.slice.call(this.el_.children),
                        s = o[0],
                        c = o[1];
                    this.data_ = n, this.meta_ = s, this.list_ = c, this.message_ = {
                        placeholder: this.meta_.textContent,
                        none: a("search.result.none"),
                        one: a("search.result.one"),
                        other: a("search.result.other")
                    };
                    var l = a("search.tokenizer");
                    l.length && (i.default.tokenizer.separator = l), this.lang_ = a("search.language").split(",").filter(Boolean).map(function(e) {
                        return e.trim()
                    })
                }
                return t.prototype.update = function(t) {
                    var n = this;
                    if ("focus" !== t.type || this.index_) {
                        if ("focus" === t.type || "keyup" === t.type) {
                            var o = t.target;
                            if (!(o instanceof HTMLInputElement)) throw new ReferenceError;
                            if (!this.index_ || o.value === this.value_) return;
                            for (; this.list_.firstChild;) this.list_.removeChild(this.list_.firstChild);
                            if (this.value_ = o.value, 0 === this.value_.length) return void(this.meta_.textContent = this.message_.placeholder);
                            var s = this.index_.query(function(e) {
                                    n.value_.toLowerCase().split(" ").filter(Boolean).forEach(function(t) {
                                        e.term(t, {
                                            wildcard: i.default.Query.wildcard.TRAILING
                                        })
                                    })
                                }).reduce(function(e, t) {
                                    var r = n.docs_.get(t.ref);
                                    if (r.parent) {
                                        var i = r.parent.location;
                                        e.set(i, (e.get(i) || []).concat(t))
                                    } else {
                                        var o = r.location;
                                        e.set(o, e.get(o) || [])
                                    }
                                    return e
                                }, new Map),
                                c = (0, r.default)(this.value_.trim()).replace(new RegExp(i.default.tokenizer.separator, "img"), "|"),
                                l = new RegExp("(^|" + i.default.tokenizer.separator + ")(" + c + ")", "img"),
                                u = function(e, t, n) {
                                    return t + "<em>" + n + "</em>"
                                };
                            this.stack_ = [], s.forEach(function(t, r) {
                                var i, o = n.docs_.get(r),
                                    a = e.createElement("li", {
                                        class: "md-search-result__item"
                                    }, e.createElement("a", {
                                        href: o.location,
                                        title: o.title,
                                        class: "md-search-result__link",
                                        tabindex: "-1"
                                    }, e.createElement("article", {
                                        class: "md-search-result__article md-search-result__article--document"
                                    }, e.createElement("h1", {
                                        class: "md-search-result__title"
                                    }, {
                                        __html: o.title.replace(l, u)
                                    }), o.text.length ? e.createElement("p", {
                                        class: "md-search-result__teaser"
                                    }, {
                                        __html: o.text.replace(l, u)
                                    }) : {}))),
                                    s = t.map(function(t) {
                                        return function() {
                                            var r = n.docs_.get(t.ref);
                                            a.appendChild(e.createElement("a", {
                                                href: r.location,
                                                title: r.title,
                                                class: "md-search-result__link",
                                                "data-md-rel": "anchor",
                                                tabindex: "-1"
                                            }, e.createElement("article", {
                                                class: "md-search-result__article"
                                            }, e.createElement("h1", {
                                                class: "md-search-result__title"
                                            }, {
                                                __html: r.title.replace(l, u)
                                            }), r.text.length ? e.createElement("p", {
                                                class: "md-search-result__teaser"
                                            }, {
                                                __html: function(e, t) {
                                                    var n = t;
                                                    if (e.length > n) {
                                                        for (;
                                                            " " !== e[n] && --n > 0;);
                                                        return e.substring(0, n) + "..."
                                                    }
                                                    return e
                                                }(r.text.replace(l, u), 400)
                                            }) : {})))
                                        }
                                    });
                                (i = n.stack_).push.apply(i, [function() {
                                    return n.list_.appendChild(a)
                                }].concat(s))
                            });
                            var d = this.el_.parentNode;
                            if (!(d instanceof HTMLElement)) throw new ReferenceError;
                            for (; this.stack_.length && d.offsetHeight >= d.scrollHeight - 16;) this.stack_.shift()();
                            var f = this.list_.querySelectorAll("[data-md-rel=anchor]");
                            switch (Array.prototype.forEach.call(f, function(e) {
                                ["click", "keydown"].forEach(function(t) {
                                    e.addEventListener(t, function(n) {
                                        if ("keydown" !== t || 13 === n.keyCode) {
                                            var r = document.querySelector("[data-md-toggle=search]");
                                            if (!(r instanceof HTMLInputElement)) throw new ReferenceError;
                                            r.checked && (r.checked = !1, r.dispatchEvent(new CustomEvent("change"))), n.preventDefault(), setTimeout(function() {
                                                document.location.href = e.href
                                            }, 100)
                                        }
                                    })
                                })
                            }), s.size) {
                                case 0:
                                    this.meta_.textContent = this.message_.none;
                                    break;
                                case 1:
                                    this.meta_.textContent = this.message_.one;
                                    break;
                                default:
                                    this.meta_.textContent = this.message_.other.replace("#", s.size)
                            }
                        }
                    } else {
                        var h = function(e) {
                            n.docs_ = e.reduce(function(e, t) {
                                var n = t.location.split("#"),
                                    r = n[0];
                                return n[1] && (t.parent = e.get(r), t.parent && !t.parent.done && (t.parent.title = t.title, t.parent.text = t.text, t.parent.done = !0)), t.text = t.text.replace(/\n/g, " ").replace(/\s+/g, " ").replace(/\s+([,.:;!?])/g, function(e, t) {
                                    return t
                                }), t.parent && t.parent.title === t.title || e.set(t.location, t), e
                            }, new Map);
                            var t = n.docs_,
                                r = n.lang_;
                            n.stack_ = [], n.index_ = (0, i.default)(function() {
                                var e, n = this,
                                    o = {
                                        "search.pipeline.trimmer": i.default.trimmer,
                                        "search.pipeline.stopwords": i.default.stopWordFilter
                                    },
                                    s = Object.keys(o).reduce(function(e, t) {
                                        return a(t).match(/^false$/i) || e.push(o[t]), e
                                    }, []);
                                this.pipeline.reset(), s && (e = this.pipeline).add.apply(e, s), 1 === r.length && "en" !== r[0] && i.default[r[0]] ? this.use(i.default[r[0]]) : r.length > 1 && this.use(i.default.multiLanguage.apply(i.default, r)), this.field("title", {
                                    boost: 10
                                }), this.field("text"), this.ref("location"), t.forEach(function(e) {
                                    return n.add(e)
                                })
                            });
                            var o = n.el_.parentNode;
                            if (!(o instanceof HTMLElement)) throw new ReferenceError;
                            o.addEventListener("scroll", function() {
                                for (; n.stack_.length && o.scrollTop + o.offsetHeight >= o.scrollHeight - 16;) n.stack_.splice(0, 10).forEach(function(e) {
                                    return e()
                                })
                            })
                        };
                        setTimeout(function() {
                            return "function" == typeof n.data_ ? n.data_().then(h) : h(n.data_)
                        }, 250)
                    }
                }, t
            }();
        t.default = s
    }).call(t, n(0))
}, function(e, t, n) {
    "use strict";
    var r = /[|\\{}()[\]^$+*?.]/g;
    e.exports = function(e) {
        if ("string" != typeof e) throw new TypeError("Expected a string");
        return e.replace(r, "\\$&")
    }
}, function(e, t, n) {
    (function(t) {
        e.exports = t.lunr = n(35)
    }).call(t, n(1))
}, function(e, t, n) {
    var r, i;
    ! function() {
        var o, a, s, c, l, u, d, f, h, p, m, v, y, g, w, _, E, x, b, k, T, S, L, C, M, O, A = function(e) {
            var t = new A.Builder;
            return t.pipeline.add(A.trimmer, A.stopWordFilter, A.stemmer), t.searchPipeline.add(A.stemmer), e.call(t, t), t.build()
        };
        A.version = "2.1.5", A.utils = {}, A.utils.warn = (o = this, function(e) {
            o.console && console.warn && console.warn(e)
        }), A.utils.asString = function(e) {
            return void 0 === e || null === e ? "" : e.toString()
        }, A.FieldRef = function(e, t, n) {
            this.docRef = e, this.fieldName = t, this._stringValue = n
        }, A.FieldRef.joiner = "/", A.FieldRef.fromString = function(e) {
            var t = e.indexOf(A.FieldRef.joiner);
            if (-1 === t) throw "malformed field ref string";
            var n = e.slice(0, t),
                r = e.slice(t + 1);
            return new A.FieldRef(r, n, e)
        }, A.FieldRef.prototype.toString = function() {
            return void 0 == this._stringValue && (this._stringValue = this.fieldName + A.FieldRef.joiner + this.docRef), this._stringValue
        }, A.idf = function(e, t) {
            var n = 0;
            for (var r in e) "_index" != r && (n += Object.keys(e[r]).length);
            var i = (t - n + .5) / (n + .5);
            return Math.log(1 + Math.abs(i))
        }, A.Token = function(e, t) {
            this.str = e || "", this.metadata = t || {}
        }, A.Token.prototype.toString = function() {
            return this.str
        }, A.Token.prototype.update = function(e) {
            return this.str = e(this.str, this.metadata), this
        }, A.Token.prototype.clone = function(e) {
            return e = e || function(e) {
                return e
            }, new A.Token(e(this.str, this.metadata), this.metadata)
        }, A.tokenizer = function(e) {
            if (null == e || void 0 == e) return [];
            if (Array.isArray(e)) return e.map(function(e) {
                return new A.Token(A.utils.asString(e).toLowerCase())
            });
            for (var t = e.toString().trim().toLowerCase(), n = t.length, r = [], i = 0, o = 0; i <= n; i++) {
                var a = i - o;
                (t.charAt(i).match(A.tokenizer.separator) || i == n) && (a > 0 && r.push(new A.Token(t.slice(o, i), {
                    position: [o, a],
                    index: r.length
                })), o = i + 1)
            }
            return r
        }, A.tokenizer.separator = /[\s\-]+/, A.Pipeline = function() {
            this._stack = []
        }, A.Pipeline.registeredFunctions = Object.create(null), A.Pipeline.registerFunction = function(e, t) {
            t in this.registeredFunctions && A.utils.warn("Overwriting existing registered function: " + t), e.label = t, A.Pipeline.registeredFunctions[e.label] = e
        }, A.Pipeline.warnIfFunctionNotRegistered = function(e) {
            e.label && e.label in this.registeredFunctions || A.utils.warn("Function is not registered with pipeline. This may cause problems when serialising the index.\n", e)
        }, A.Pipeline.load = function(e) {
            var t = new A.Pipeline;
            return e.forEach(function(e) {
                var n = A.Pipeline.registeredFunctions[e];
                if (!n) throw new Error("Cannot load unregistered function: " + e);
                t.add(n)
            }), t
        }, A.Pipeline.prototype.add = function() {
            Array.prototype.slice.call(arguments).forEach(function(e) {
                A.Pipeline.warnIfFunctionNotRegistered(e), this._stack.push(e)
            }, this)
        }, A.Pipeline.prototype.after = function(e, t) {
            A.Pipeline.warnIfFunctionNotRegistered(t);
            var n = this._stack.indexOf(e);
            if (-1 == n) throw new Error("Cannot find existingFn");
            n += 1, this._stack.splice(n, 0, t)
        }, A.Pipeline.prototype.before = function(e, t) {
            A.Pipeline.warnIfFunctionNotRegistered(t);
            var n = this._stack.indexOf(e);
            if (-1 == n) throw new Error("Cannot find existingFn");
            this._stack.splice(n, 0, t)
        }, A.Pipeline.prototype.remove = function(e) {
            var t = this._stack.indexOf(e); - 1 != t && this._stack.splice(t, 1)
        }, A.Pipeline.prototype.run = function(e) {
            for (var t = this._stack.length, n = 0; n < t; n++) {
                var r = this._stack[n];
                e = e.reduce(function(t, n, i) {
                    var o = r(n, i, e);
                    return void 0 === o || "" === o ? t : t.concat(o)
                }, [])
            }
            return e
        }, A.Pipeline.prototype.runString = function(e) {
            var t = new A.Token(e);
            return this.run([t]).map(function(e) {
                return e.toString()
            })
        }, A.Pipeline.prototype.reset = function() {
            this._stack = []
        }, A.Pipeline.prototype.toJSON = function() {
            return this._stack.map(function(e) {
                return A.Pipeline.warnIfFunctionNotRegistered(e), e.label
            })
        }, A.Vector = function(e) {
            this._magnitude = 0, this.elements = e || []
        }, A.Vector.prototype.positionForIndex = function(e) {
            if (0 == this.elements.length) return 0;
            for (var t = 0, n = this.elements.length / 2, r = n - t, i = Math.floor(r / 2), o = this.elements[2 * i]; r > 1 && (o < e && (t = i), o > e && (n = i), o != e);) r = n - t, i = t + Math.floor(r / 2), o = this.elements[2 * i];
            return o == e ? 2 * i : o > e ? 2 * i : o < e ? 2 * (i + 1) : void 0
        }, A.Vector.prototype.insert = function(e, t) {
            this.upsert(e, t, function() {
                throw "duplicate index"
            })
        }, A.Vector.prototype.upsert = function(e, t, n) {
            this._magnitude = 0;
            var r = this.positionForIndex(e);
            this.elements[r] == e ? this.elements[r + 1] = n(this.elements[r + 1], t) : this.elements.splice(r, 0, e, t)
        }, A.Vector.prototype.magnitude = function() {
            if (this._magnitude) return this._magnitude;
            for (var e = 0, t = this.elements.length, n = 1; n < t; n += 2) {
                var r = this.elements[n];
                e += r * r
            }
            return this._magnitude = Math.sqrt(e)
        }, A.Vector.prototype.dot = function(e) {
            for (var t = 0, n = this.elements, r = e.elements, i = n.length, o = r.length, a = 0, s = 0, c = 0, l = 0; c < i && l < o;)(a = n[c]) < (s = r[l]) ? c += 2 : a > s ? l += 2 : a == s && (t += n[c + 1] * r[l + 1], c += 2, l += 2);
            return t
        }, A.Vector.prototype.similarity = function(e) {
            return this.dot(e) / (this.magnitude() * e.magnitude())
        }, A.Vector.prototype.toArray = function() {
            for (var e = new Array(this.elements.length / 2), t = 1, n = 0; t < this.elements.length; t += 2, n++) e[n] = this.elements[t];
            return e
        }, A.Vector.prototype.toJSON = function() {
            return this.elements
        }, A.stemmer = (a = {
            ational: "ate",
            tional: "tion",
            enci: "ence",
            anci: "ance",
            izer: "ize",
            bli: "ble",
            alli: "al",
            entli: "ent",
            eli: "e",
            ousli: "ous",
            ization: "ize",
            ation: "ate",
            ator: "ate",
            alism: "al",
            iveness: "ive",
            fulness: "ful",
            ousness: "ous",
            aliti: "al",
            iviti: "ive",
            biliti: "ble",
            logi: "log"
        }, s = {
            icate: "ic",
            ative: "",
            alize: "al",
            iciti: "ic",
            ical: "ic",
            ful: "",
            ness: ""
        }, c = "[aeiouy]", l = "[^aeiou][^aeiouy]*", u = new RegExp("^([^aeiou][^aeiouy]*)?[aeiouy][aeiou]*[^aeiou][^aeiouy]*"), d = new RegExp("^([^aeiou][^aeiouy]*)?[aeiouy][aeiou]*[^aeiou][^aeiouy]*[aeiouy][aeiou]*[^aeiou][^aeiouy]*"), f = new RegExp("^([^aeiou][^aeiouy]*)?[aeiouy][aeiou]*[^aeiou][^aeiouy]*([aeiouy][aeiou]*)?$"), h = new RegExp("^([^aeiou][^aeiouy]*)?[aeiouy]"), p = /^(.+?)(ss|i)es$/, m = /^(.+?)([^s])s$/, v = /^(.+?)eed$/, y = /^(.+?)(ed|ing)$/, g = /.$/, w = /(at|bl|iz)$/, _ = new RegExp("([^aeiouylsz])\\1$"), E = new RegExp("^" + l + c + "[^aeiouwxy]$"), x = /^(.+?[^aeiou])y$/, b = /^(.+?)(ational|tional|enci|anci|izer|bli|alli|entli|eli|ousli|ization|ation|ator|alism|iveness|fulness|ousness|aliti|iviti|biliti|logi)$/, k = /^(.+?)(icate|ative|alize|iciti|ical|ful|ness)$/, T = /^(.+?)(al|ance|ence|er|ic|able|ible|ant|ement|ment|ent|ou|ism|ate|iti|ous|ive|ize)$/, S = /^(.+?)(s|t)(ion)$/, L = /^(.+?)e$/, C = /ll$/, M = new RegExp("^" + l + c + "[^aeiouwxy]$"), O = function(e) {
            var t, n, r, i, o, c, l;
            if (e.length < 3) return e;
            if ("y" == (r = e.substr(0, 1)) && (e = r.toUpperCase() + e.substr(1)), o = m, (i = p).test(e) ? e = e.replace(i, "$1$2") : o.test(e) && (e = e.replace(o, "$1$2")), o = y, (i = v).test(e)) {
                var O = i.exec(e);
                (i = u).test(O[1]) && (i = g, e = e.replace(i, ""))
            } else if (o.test(e)) {
                t = (O = o.exec(e))[1], (o = h).test(t) && (c = _, l = E, (o = w).test(e = t) ? e += "e" : c.test(e) ? (i = g, e = e.replace(i, "")) : l.test(e) && (e += "e"))
            }(i = x).test(e) && (e = (t = (O = i.exec(e))[1]) + "i");
            (i = b).test(e) && (t = (O = i.exec(e))[1], n = O[2], (i = u).test(t) && (e = t + a[n]));
            (i = k).test(e) && (t = (O = i.exec(e))[1], n = O[2], (i = u).test(t) && (e = t + s[n]));
            if (o = S, (i = T).test(e)) t = (O = i.exec(e))[1], (i = d).test(t) && (e = t);
            else if (o.test(e)) {
                t = (O = o.exec(e))[1] + O[2], (o = d).test(t) && (e = t)
            }(i = L).test(e) && (t = (O = i.exec(e))[1], o = f, c = M, ((i = d).test(t) || o.test(t) && !c.test(t)) && (e = t));
            return o = d, (i = C).test(e) && o.test(e) && (i = g, e = e.replace(i, "")), "y" == r && (e = r.toLowerCase() + e.substr(1)), e
        }, function(e) {
            return e.update(O)
        }), A.Pipeline.registerFunction(A.stemmer, "stemmer"), A.generateStopWordFilter = function(e) {
            var t = e.reduce(function(e, t) {
                return e[t] = t, e
            }, {});
            return function(e) {
                if (e && t[e.toString()] !== e.toString()) return e
            }
        }, A.stopWordFilter = A.generateStopWordFilter(["a", "able", "about", "across", "after", "all", "almost", "also", "am", "among", "an", "and", "any", "are", "as", "at", "be", "because", "been", "but", "by", "can", "cannot", "could", "dear", "did", "do", "does", "either", "else", "ever", "every", "for", "from", "get", "got", "had", "has", "have", "he", "her", "hers", "him", "his", "how", "however", "i", "if", "in", "into", "is", "it", "its", "just", "least", "let", "like", "likely", "may", "me", "might", "most", "must", "my", "neither", "no", "nor", "not", "of", "off", "often", "on", "only", "or", "other", "our", "own", "rather", "said", "say", "says", "she", "should", "since", "so", "some", "than", "that", "the", "their", "them", "then", "there", "these", "they", "this", "tis", "to", "too", "twas", "us", "wants", "was", "we", "were", "what", "when", "where", "which", "while", "who", "whom", "why", "will", "with", "would", "yet", "you", "your"]), A.Pipeline.registerFunction(A.stopWordFilter, "stopWordFilter"), A.trimmer = function(e) {
            return e.update(function(e) {
                return e.replace(/^\W+/, "").replace(/\W+$/, "")
            })
        }, A.Pipeline.registerFunction(A.trimmer, "trimmer"), A.TokenSet = function() {
            this.final = !1, this.edges = {}, this.id = A.TokenSet._nextId, A.TokenSet._nextId += 1
        }, A.TokenSet._nextId = 1, A.TokenSet.fromArray = function(e) {
            for (var t = new A.TokenSet.Builder, n = 0, r = e.length; n < r; n++) t.insert(e[n]);
            return t.finish(), t.root
        }, A.TokenSet.fromClause = function(e) {
            return "editDistance" in e ? A.TokenSet.fromFuzzyString(e.term, e.editDistance) : A.TokenSet.fromString(e.term)
        }, A.TokenSet.fromFuzzyString = function(e, t) {
            for (var n = new A.TokenSet, r = [{
                    node: n,
                    editsRemaining: t,
                    str: e
                }]; r.length;) {
                var i, o, a, s = r.pop();
                if (s.str.length > 0)(o = s.str.charAt(0)) in s.node.edges ? i = s.node.edges[o] : (i = new A.TokenSet, s.node.edges[o] = i), 1 == s.str.length ? i.final = !0 : r.push({
                    node: i,
                    editsRemaining: s.editsRemaining,
                    str: s.str.slice(1)
                });
                if (s.editsRemaining > 0 && s.str.length > 1)(o = s.str.charAt(1)) in s.node.edges ? a = s.node.edges[o] : (a = new A.TokenSet, s.node.edges[o] = a), s.str.length <= 2 ? a.final = !0 : r.push({
                    node: a,
                    editsRemaining: s.editsRemaining - 1,
                    str: s.str.slice(2)
                });
                if (s.editsRemaining > 0 && 1 == s.str.length && (s.node.final = !0), s.editsRemaining > 0 && s.str.length >= 1) {
                    if ("*" in s.node.edges) var c = s.node.edges["*"];
                    else {
                        c = new A.TokenSet;
                        s.node.edges["*"] = c
                    }
                    1 == s.str.length ? c.final = !0 : r.push({
                        node: c,
                        editsRemaining: s.editsRemaining - 1,
                        str: s.str.slice(1)
                    })
                }
                if (s.editsRemaining > 0) {
                    if ("*" in s.node.edges) var l = s.node.edges["*"];
                    else {
                        l = new A.TokenSet;
                        s.node.edges["*"] = l
                    }
                    0 == s.str.length ? l.final = !0 : r.push({
                        node: l,
                        editsRemaining: s.editsRemaining - 1,
                        str: s.str
                    })
                }
                if (s.editsRemaining > 0 && s.str.length > 1) {
                    var u, d = s.str.charAt(0),
                        f = s.str.charAt(1);
                    f in s.node.edges ? u = s.node.edges[f] : (u = new A.TokenSet, s.node.edges[f] = u), 1 == s.str.length ? u.final = !0 : r.push({
                        node: u,
                        editsRemaining: s.editsRemaining - 1,
                        str: d + s.str.slice(2)
                    })
                }
            }
            return n
        }, A.TokenSet.fromString = function(e) {
            for (var t = new A.TokenSet, n = t, r = !1, i = 0, o = e.length; i < o; i++) {
                var a = e[i],
                    s = i == o - 1;
                if ("*" == a) r = !0, t.edges[a] = t, t.final = s;
                else {
                    var c = new A.TokenSet;
                    c.final = s, t.edges[a] = c, t = c, r && (t.edges["*"] = n)
                }
            }
            return n
        }, A.TokenSet.prototype.toArray = function() {
            for (var e = [], t = [{
                    prefix: "",
                    node: this
                }]; t.length;) {
                var n = t.pop(),
                    r = Object.keys(n.node.edges),
                    i = r.length;
                n.node.final && e.push(n.prefix);
                for (var o = 0; o < i; o++) {
                    var a = r[o];
                    t.push({
                        prefix: n.prefix.concat(a),
                        node: n.node.edges[a]
                    })
                }
            }
            return e
        }, A.TokenSet.prototype.toString = function() {
            if (this._str) return this._str;
            for (var e = this.final ? "1" : "0", t = Object.keys(this.edges).sort(), n = t.length, r = 0; r < n; r++) {
                var i = t[r];
                e = e + i + this.edges[i].id
            }
            return e
        }, A.TokenSet.prototype.intersect = function(e) {
            for (var t = new A.TokenSet, n = void 0, r = [{
                    qNode: e,
                    output: t,
                    node: this
                }]; r.length;) {
                n = r.pop();
                for (var i = Object.keys(n.qNode.edges), o = i.length, a = Object.keys(n.node.edges), s = a.length, c = 0; c < o; c++)
                    for (var l = i[c], u = 0; u < s; u++) {
                        var d = a[u];
                        if (d == l || "*" == l) {
                            var f = n.node.edges[d],
                                h = n.qNode.edges[l],
                                p = f.final && h.final,
                                m = void 0;
                            d in n.output.edges ? (m = n.output.edges[d]).final = m.final || p : ((m = new A.TokenSet).final = p, n.output.edges[d] = m), r.push({
                                qNode: h,
                                output: m,
                                node: f
                            })
                        }
                    }
            }
            return t
        }, A.TokenSet.Builder = function() {
            this.previousWord = "", this.root = new A.TokenSet, this.uncheckedNodes = [], this.minimizedNodes = {}
        }, A.TokenSet.Builder.prototype.insert = function(e) {
            var t, n = 0;
            if (e < this.previousWord) throw new Error("Out of order word insertion");
            for (var r = 0; r < e.length && r < this.previousWord.length && e[r] == this.previousWord[r]; r++) n++;
            this.minimize(n), t = 0 == this.uncheckedNodes.length ? this.root : this.uncheckedNodes[this.uncheckedNodes.length - 1].child;
            for (r = n; r < e.length; r++) {
                var i = new A.TokenSet,
                    o = e[r];
                t.edges[o] = i, this.uncheckedNodes.push({
                    parent: t,
                    char: o,
                    child: i
                }), t = i
            }
            t.final = !0, this.previousWord = e
        }, A.TokenSet.Builder.prototype.finish = function() {
            this.minimize(0)
        }, A.TokenSet.Builder.prototype.minimize = function(e) {
            for (var t = this.uncheckedNodes.length - 1; t >= e; t--) {
                var n = this.uncheckedNodes[t],
                    r = n.child.toString();
                r in this.minimizedNodes ? n.parent.edges[n.char] = this.minimizedNodes[r] : (n.child._str = r, this.minimizedNodes[r] = n.child), this.uncheckedNodes.pop()
            }
        }, A.Index = function(e) {
            this.invertedIndex = e.invertedIndex, this.fieldVectors = e.fieldVectors, this.tokenSet = e.tokenSet, this.fields = e.fields, this.pipeline = e.pipeline
        }, A.Index.prototype.search = function(e) {
            return this.query(function(t) {
                new A.QueryParser(e, t).parse()
            })
        }, A.Index.prototype.query = function(e) {
            var t = new A.Query(this.fields),
                n = Object.create(null),
                r = Object.create(null),
                i = Object.create(null);
            e.call(t, t);
            for (var o = 0; o < t.clauses.length; o++) {
                var a = t.clauses[o],
                    s = null;
                s = a.usePipeline ? this.pipeline.runString(a.term) : [a.term];
                for (var c = 0; c < s.length; c++) {
                    var l = s[c];
                    a.term = l;
                    for (var u = A.TokenSet.fromClause(a), d = this.tokenSet.intersect(u).toArray(), f = 0; f < d.length; f++)
                        for (var h = d[f], p = this.invertedIndex[h], m = p._index, v = 0; v < a.fields.length; v++) {
                            var y = a.fields[v],
                                g = p[y],
                                w = Object.keys(g),
                                _ = h + "/" + y;
                            if (void 0 === r[y] && (r[y] = new A.Vector), r[y].upsert(m, 1 * a.boost, function(e, t) {
                                    return e + t
                                }), !i[_]) {
                                for (var E = 0; E < w.length; E++) {
                                    var x, b = w[E],
                                        k = new A.FieldRef(b, y),
                                        T = g[b];
                                    void 0 === (x = n[k]) ? n[k] = new A.MatchData(h, y, T) : x.add(h, y, T)
                                }
                                i[_] = !0
                            }
                        }
                }
            }
            var S = Object.keys(n),
                L = [],
                C = Object.create(null);
            for (o = 0; o < S.length; o++) {
                var M, O = A.FieldRef.fromString(S[o]),
                    R = O.docRef,
                    I = this.fieldVectors[O],
                    P = r[O.fieldName].similarity(I);
                if (void 0 !== (M = C[R])) M.score += P, M.matchData.combine(n[O]);
                else {
                    var N = {
                        ref: R,
                        score: P,
                        matchData: n[O]
                    };
                    C[R] = N, L.push(N)
                }
            }
            return L.sort(function(e, t) {
                return t.score - e.score
            })
        }, A.Index.prototype.toJSON = function() {
            var e = Object.keys(this.invertedIndex).sort().map(function(e) {
                    return [e, this.invertedIndex[e]]
                }, this),
                t = Object.keys(this.fieldVectors).map(function(e) {
                    return [e, this.fieldVectors[e].toJSON()]
                }, this);
            return {
                version: A.version,
                fields: this.fields,
                fieldVectors: t,
                invertedIndex: e,
                pipeline: this.pipeline.toJSON()
            }
        }, A.Index.load = function(e) {
            var t = {},
                n = {},
                r = e.fieldVectors,
                i = {},
                o = e.invertedIndex,
                a = new A.TokenSet.Builder,
                s = A.Pipeline.load(e.pipeline);
            e.version != A.version && A.utils.warn("Version mismatch when loading serialised index. Current version of lunr '" + A.version + "' does not match serialized index '" + e.version + "'");
            for (var c = 0; c < r.length; c++) {
                var l = (d = r[c])[0],
                    u = d[1];
                n[l] = new A.Vector(u)
            }
            for (c = 0; c < o.length; c++) {
                var d, f = (d = o[c])[0],
                    h = d[1];
                a.insert(f), i[f] = h
            }
            return a.finish(), t.fields = e.fields, t.fieldVectors = n, t.invertedIndex = i, t.tokenSet = a.root, t.pipeline = s, new A.Index(t)
        }, A.Builder = function() {
            this._ref = "id", this._fields = [], this.invertedIndex = Object.create(null), this.fieldTermFrequencies = {}, this.fieldLengths = {}, this.tokenizer = A.tokenizer, this.pipeline = new A.Pipeline, this.searchPipeline = new A.Pipeline, this.documentCount = 0, this._b = .75, this._k1 = 1.2, this.termIndex = 0, this.metadataWhitelist = []
        }, A.Builder.prototype.ref = function(e) {
            this._ref = e
        }, A.Builder.prototype.field = function(e) {
            this._fields.push(e)
        }, A.Builder.prototype.b = function(e) {
            this._b = e < 0 ? 0 : e > 1 ? 1 : e
        }, A.Builder.prototype.k1 = function(e) {
            this._k1 = e
        }, A.Builder.prototype.add = function(e) {
            var t = e[this._ref];
            this.documentCount += 1;
            for (var n = 0; n < this._fields.length; n++) {
                var r = this._fields[n],
                    i = e[r],
                    o = this.tokenizer(i),
                    a = this.pipeline.run(o),
                    s = new A.FieldRef(t, r),
                    c = Object.create(null);
                this.fieldTermFrequencies[s] = c, this.fieldLengths[s] = 0, this.fieldLengths[s] += a.length;
                for (var l = 0; l < a.length; l++) {
                    var u = a[l];
                    if (void 0 == c[u] && (c[u] = 0), c[u] += 1, void 0 == this.invertedIndex[u]) {
                        var d = Object.create(null);
                        d._index = this.termIndex, this.termIndex += 1;
                        for (var f = 0; f < this._fields.length; f++) d[this._fields[f]] = Object.create(null);
                        this.invertedIndex[u] = d
                    }
                    void 0 == this.invertedIndex[u][r][t] && (this.invertedIndex[u][r][t] = Object.create(null));
                    for (var h = 0; h < this.metadataWhitelist.length; h++) {
                        var p = this.metadataWhitelist[h],
                            m = u.metadata[p];
                        void 0 == this.invertedIndex[u][r][t][p] && (this.invertedIndex[u][r][t][p] = []), this.invertedIndex[u][r][t][p].push(m)
                    }
                }
            }
        }, A.Builder.prototype.calculateAverageFieldLengths = function() {
            for (var e = Object.keys(this.fieldLengths), t = e.length, n = {}, r = {}, i = 0; i < t; i++) {
                var o = A.FieldRef.fromString(e[i]);
                r[a = o.fieldName] || (r[a] = 0), r[a] += 1, n[a] || (n[a] = 0), n[a] += this.fieldLengths[o]
            }
            for (i = 0; i < this._fields.length; i++) {
                var a;
                n[a = this._fields[i]] = n[a] / r[a]
            }
            this.averageFieldLength = n
        }, A.Builder.prototype.createFieldVectors = function() {
            for (var e = {}, t = Object.keys(this.fieldTermFrequencies), n = t.length, r = Object.create(null), i = 0; i < n; i++) {
                for (var o = A.FieldRef.fromString(t[i]), a = o.fieldName, s = this.fieldLengths[o], c = new A.Vector, l = this.fieldTermFrequencies[o], u = Object.keys(l), d = u.length, f = 0; f < d; f++) {
                    var h, p, m, v = u[f],
                        y = l[v],
                        g = this.invertedIndex[v]._index;
                    void 0 === r[v] ? (h = A.idf(this.invertedIndex[v], this.documentCount), r[v] = h) : h = r[v], p = h * ((this._k1 + 1) * y) / (this._k1 * (1 - this._b + this._b * (s / this.averageFieldLength[a])) + y), m = Math.round(1e3 * p) / 1e3, c.insert(g, m)
                }
                e[o] = c
            }
            this.fieldVectors = e
        }, A.Builder.prototype.createTokenSet = function() {
            this.tokenSet = A.TokenSet.fromArray(Object.keys(this.invertedIndex).sort())
        }, A.Builder.prototype.build = function() {
            return this.calculateAverageFieldLengths(), this.createFieldVectors(), this.createTokenSet(), new A.Index({
                invertedIndex: this.invertedIndex,
                fieldVectors: this.fieldVectors,
                tokenSet: this.tokenSet,
                fields: this._fields,
                pipeline: this.searchPipeline
            })
        }, A.Builder.prototype.use = function(e) {
            var t = Array.prototype.slice.call(arguments, 1);
            t.unshift(this), e.apply(this, t)
        }, A.MatchData = function(e, t, n) {
            for (var r = Object.create(null), i = Object.keys(n), o = 0; o < i.length; o++) {
                var a = i[o];
                r[a] = n[a].slice()
            }
            this.metadata = Object.create(null), this.metadata[e] = Object.create(null), this.metadata[e][t] = r
        }, A.MatchData.prototype.combine = function(e) {
            for (var t = Object.keys(e.metadata), n = 0; n < t.length; n++) {
                var r = t[n],
                    i = Object.keys(e.metadata[r]);
                void 0 == this.metadata[r] && (this.metadata[r] = Object.create(null));
                for (var o = 0; o < i.length; o++) {
                    var a = i[o],
                        s = Object.keys(e.metadata[r][a]);
                    void 0 == this.metadata[r][a] && (this.metadata[r][a] = Object.create(null));
                    for (var c = 0; c < s.length; c++) {
                        var l = s[c];
                        void 0 == this.metadata[r][a][l] ? this.metadata[r][a][l] = e.metadata[r][a][l] : this.metadata[r][a][l] = this.metadata[r][a][l].concat(e.metadata[r][a][l])
                    }
                }
            }
        }, A.MatchData.prototype.add = function(e, t, n) {
            if (!(e in this.metadata)) return this.metadata[e] = Object.create(null), void(this.metadata[e][t] = n);
            if (t in this.metadata[e])
                for (var r = Object.keys(n), i = 0; i < r.length; i++) {
                    var o = r[i];
                    o in this.metadata[e][t] ? this.metadata[e][t][o] = this.metadata[e][t][o].concat(n[o]) : this.metadata[e][t][o] = n[o]
                } else this.metadata[e][t] = n
        }, A.Query = function(e) {
            this.clauses = [], this.allFields = e
        }, A.Query.wildcard = new String("*"), A.Query.wildcard.NONE = 0, A.Query.wildcard.LEADING = 1, A.Query.wildcard.TRAILING = 2, A.Query.prototype.clause = function(e) {
            return "fields" in e || (e.fields = this.allFields), "boost" in e || (e.boost = 1), "usePipeline" in e || (e.usePipeline = !0), "wildcard" in e || (e.wildcard = A.Query.wildcard.NONE), e.wildcard & A.Query.wildcard.LEADING && e.term.charAt(0) != A.Query.wildcard && (e.term = "*" + e.term), e.wildcard & A.Query.wildcard.TRAILING && e.term.slice(-1) != A.Query.wildcard && (e.term = e.term + "*"), this.clauses.push(e), this
        }, A.Query.prototype.term = function(e, t) {
            var n = t || {};
            return n.term = e, this.clause(n), this
        }, A.QueryParseError = function(e, t, n) {
            this.name = "QueryParseError", this.message = e, this.start = t, this.end = n
        }, A.QueryParseError.prototype = new Error, A.QueryLexer = function(e) {
            this.lexemes = [], this.str = e, this.length = e.length, this.pos = 0, this.start = 0, this.escapeCharPositions = []
        }, A.QueryLexer.prototype.run = function() {
            for (var e = A.QueryLexer.lexText; e;) e = e(this)
        }, A.QueryLexer.prototype.sliceString = function() {
            for (var e = [], t = this.start, n = this.pos, r = 0; r < this.escapeCharPositions.length; r++) n = this.escapeCharPositions[r], e.push(this.str.slice(t, n)), t = n + 1;
            return e.push(this.str.slice(t, this.pos)), this.escapeCharPositions.length = 0, e.join("")
        }, A.QueryLexer.prototype.emit = function(e) {
            this.lexemes.push({
                type: e,
                str: this.sliceString(),
                start: this.start,
                end: this.pos
            }), this.start = this.pos
        }, A.QueryLexer.prototype.escapeCharacter = function() {
            this.escapeCharPositions.push(this.pos - 1), this.pos += 1
        }, A.QueryLexer.prototype.next = function() {
            if (this.pos >= this.length) return A.QueryLexer.EOS;
            var e = this.str.charAt(this.pos);
            return this.pos += 1, e
        }, A.QueryLexer.prototype.width = function() {
            return this.pos - this.start
        }, A.QueryLexer.prototype.ignore = function() {
            this.start == this.pos && (this.pos += 1), this.start = this.pos
        }, A.QueryLexer.prototype.backup = function() {
            this.pos -= 1
        }, A.QueryLexer.prototype.acceptDigitRun = function() {
            var e, t;
            do {
                t = (e = this.next()).charCodeAt(0)
            } while (t > 47 && t < 58);
            e != A.QueryLexer.EOS && this.backup()
        }, A.QueryLexer.prototype.more = function() {
            return this.pos < this.length
        }, A.QueryLexer.EOS = "EOS", A.QueryLexer.FIELD = "FIELD", A.QueryLexer.TERM = "TERM", A.QueryLexer.EDIT_DISTANCE = "EDIT_DISTANCE", A.QueryLexer.BOOST = "BOOST", A.QueryLexer.lexField = function(e) {
            return e.backup(), e.emit(A.QueryLexer.FIELD), e.ignore(), A.QueryLexer.lexText
        }, A.QueryLexer.lexTerm = function(e) {
            if (e.width() > 1 && (e.backup(), e.emit(A.QueryLexer.TERM)), e.ignore(), e.more()) return A.QueryLexer.lexText
        }, A.QueryLexer.lexEditDistance = function(e) {
            return e.ignore(), e.acceptDigitRun(), e.emit(A.QueryLexer.EDIT_DISTANCE), A.QueryLexer.lexText
        }, A.QueryLexer.lexBoost = function(e) {
            return e.ignore(), e.acceptDigitRun(), e.emit(A.QueryLexer.BOOST), A.QueryLexer.lexText
        }, A.QueryLexer.lexEOS = function(e) {
            e.width() > 0 && e.emit(A.QueryLexer.TERM)
        }, A.QueryLexer.termSeparator = A.tokenizer.separator, A.QueryLexer.lexText = function(e) {
            for (;;) {
                var t = e.next();
                if (t == A.QueryLexer.EOS) return A.QueryLexer.lexEOS;
                if (92 != t.charCodeAt(0)) {
                    if (":" == t) return A.QueryLexer.lexField;
                    if ("~" == t) return e.backup(), e.width() > 0 && e.emit(A.QueryLexer.TERM), A.QueryLexer.lexEditDistance;
                    if ("^" == t) return e.backup(), e.width() > 0 && e.emit(A.QueryLexer.TERM), A.QueryLexer.lexBoost;
                    if (t.match(A.QueryLexer.termSeparator)) return A.QueryLexer.lexTerm
                } else e.escapeCharacter()
            }
        }, A.QueryParser = function(e, t) {
            this.lexer = new A.QueryLexer(e), this.query = t, this.currentClause = {}, this.lexemeIdx = 0
        }, A.QueryParser.prototype.parse = function() {
            this.lexer.run(), this.lexemes = this.lexer.lexemes;
            for (var e = A.QueryParser.parseFieldOrTerm; e;) e = e(this);
            return this.query
        }, A.QueryParser.prototype.peekLexeme = function() {
            return this.lexemes[this.lexemeIdx]
        }, A.QueryParser.prototype.consumeLexeme = function() {
            var e = this.peekLexeme();
            return this.lexemeIdx += 1, e
        }, A.QueryParser.prototype.nextClause = function() {
            var e = this.currentClause;
            this.query.clause(e), this.currentClause = {}
        }, A.QueryParser.parseFieldOrTerm = function(e) {
            var t = e.peekLexeme();
            if (void 0 != t) switch (t.type) {
                case A.QueryLexer.FIELD:
                    return A.QueryParser.parseField;
                case A.QueryLexer.TERM:
                    return A.QueryParser.parseTerm;
                default:
                    var n = "expected either a field or a term, found " + t.type;
                    throw t.str.length >= 1 && (n += " with value '" + t.str + "'"), new A.QueryParseError(n, t.start, t.end)
            }
        }, A.QueryParser.parseField = function(e) {
            var t = e.consumeLexeme();
            if (void 0 != t) {
                if (-1 == e.query.allFields.indexOf(t.str)) {
                    var n = e.query.allFields.map(function(e) {
                            return "'" + e + "'"
                        }).join(", "),
                        r = "unrecognised field '" + t.str + "', possible fields: " + n;
                    throw new A.QueryParseError(r, t.start, t.end)
                }
                e.currentClause.fields = [t.str];
                var i = e.peekLexeme();
                if (void 0 == i) {
                    r = "expecting term, found nothing";
                    throw new A.QueryParseError(r, t.start, t.end)
                }
                switch (i.type) {
                    case A.QueryLexer.TERM:
                        return A.QueryParser.parseTerm;
                    default:
                        r = "expecting term, found '" + i.type + "'";
                        throw new A.QueryParseError(r, i.start, i.end)
                }
            }
        }, A.QueryParser.parseTerm = function(e) {
            var t = e.consumeLexeme();
            if (void 0 != t) {
                e.currentClause.term = t.str.toLowerCase(), -1 != t.str.indexOf("*") && (e.currentClause.usePipeline = !1);
                var n = e.peekLexeme();
                if (void 0 != n) switch (n.type) {
                    case A.QueryLexer.TERM:
                        return e.nextClause(), A.QueryParser.parseTerm;
                    case A.QueryLexer.FIELD:
                        return e.nextClause(), A.QueryParser.parseField;
                    case A.QueryLexer.EDIT_DISTANCE:
                        return A.QueryParser.parseEditDistance;
                    case A.QueryLexer.BOOST:
                        return A.QueryParser.parseBoost;
                    default:
                        var r = "Unexpected lexeme type '" + n.type + "'";
                        throw new A.QueryParseError(r, n.start, n.end)
                } else e.nextClause()
            }
        }, A.QueryParser.parseEditDistance = function(e) {
            var t = e.consumeLexeme();
            if (void 0 != t) {
                var n = parseInt(t.str, 10);
                if (isNaN(n)) {
                    var r = "edit distance must be numeric";
                    throw new A.QueryParseError(r, t.start, t.end)
                }
                e.currentClause.editDistance = n;
                var i = e.peekLexeme();
                if (void 0 != i) switch (i.type) {
                    case A.QueryLexer.TERM:
                        return e.nextClause(), A.QueryParser.parseTerm;
                    case A.QueryLexer.FIELD:
                        return e.nextClause(), A.QueryParser.parseField;
                    case A.QueryLexer.EDIT_DISTANCE:
                        return A.QueryParser.parseEditDistance;
                    case A.QueryLexer.BOOST:
                        return A.QueryParser.parseBoost;
                    default:
                        r = "Unexpected lexeme type '" + i.type + "'";
                        throw new A.QueryParseError(r, i.start, i.end)
                } else e.nextClause()
            }
        }, A.QueryParser.parseBoost = function(e) {
            var t = e.consumeLexeme();
            if (void 0 != t) {
                var n = parseInt(t.str, 10);
                if (isNaN(n)) {
                    var r = "boost must be numeric";
                    throw new A.QueryParseError(r, t.start, t.end)
                }
                e.currentClause.boost = n;
                var i = e.peekLexeme();
                if (void 0 != i) switch (i.type) {
                    case A.QueryLexer.TERM:
                        return e.nextClause(), A.QueryParser.parseTerm;
                    case A.QueryLexer.FIELD:
                        return e.nextClause(), A.QueryParser.parseField;
                    case A.QueryLexer.EDIT_DISTANCE:
                        return A.QueryParser.parseEditDistance;
                    case A.QueryLexer.BOOST:
                        return A.QueryParser.parseBoost;
                    default:
                        r = "Unexpected lexeme type '" + i.type + "'";
                        throw new A.QueryParseError(r, i.start, i.end)
                } else e.nextClause()
            }
        }, void 0 === (i = "function" == typeof(r = function() {
            return A
        }) ? r.call(t, n, t, e) : r) || (e.exports = i)
    }()
}, function(e, t, n) {
    "use strict";
    t.__esModule = !0;
    var r, i = n(37),
        o = (r = i) && r.__esModule ? r : {
            default: r
        };
    t.default = {
        Position: o.default
    }
}, function(e, t, n) {
    "use strict";
    t.__esModule = !0;
    var r = function() {
        function e(t, n) {
            ! function(e, t) {
                if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
            }(this, e);
            var r = "string" == typeof t ? document.querySelector(t) : t;
            if (!(r instanceof HTMLElement && r.parentNode instanceof HTMLElement)) throw new ReferenceError;
            if (this.el_ = r, this.parent_ = r.parentNode, !((r = "string" == typeof n ? document.querySelector(n) : n) instanceof HTMLElement)) throw new ReferenceError;
            this.header_ = r, this.height_ = 0, this.pad_ = "fixed" === window.getComputedStyle(this.header_).position
        }
        return e.prototype.setup = function() {
            var e = Array.prototype.reduce.call(this.parent_.children, function(e, t) {
                return Math.max(e, t.offsetTop)
            }, 0);
            this.offset_ = e - (this.pad_ ? this.header_.offsetHeight : 0), this.update()
        }, e.prototype.update = function(e) {
            var t = window.pageYOffset,
                n = window.innerHeight;
            e && "resize" === e.type && this.setup();
            var r = this.pad_ ? this.header_.offsetHeight : 0,
                i = this.parent_.offsetTop + this.parent_.offsetHeight,
                o = n - r - Math.max(0, this.offset_ - t) - Math.max(0, t + n - i);
            o !== this.height_ && (this.el_.style.height = (this.height_ = o) + "px"), t >= this.offset_ ? "lock" !== this.el_.dataset.mdState && (this.el_.dataset.mdState = "lock") : "lock" === this.el_.dataset.mdState && (this.el_.dataset.mdState = "")
        }, e.prototype.reset = function() {
            this.el_.dataset.mdState = "", this.el_.style.height = "", this.height_ = 0
        }, e
    }();
    t.default = r
}, function(e, t, n) {
    "use strict";
    t.__esModule = !0;
    var r = o(n(39)),
        i = o(n(43));

    function o(e) {
        return e && e.__esModule ? e : {
            default: e
        }
    }
    t.default = {
        Adapter: r.default,
        Repository: i.default
    }
}, function(e, t, n) {
    "use strict";
    t.__esModule = !0;
    var r, i = n(40),
        o = (r = i) && r.__esModule ? r : {
            default: r
        };
    t.default = {
        GitHub: o.default
    }
}, function(e, t, n) {
    "use strict";
    t.__esModule = !0;
    var r, i = n(41);
    var o = function(e) {
        function t(n) {
            ! function(e, t) {
                if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
            }(this, t);
            var r = function(e, t) {
                    if (!e) throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
                    return !t || "object" != typeof t && "function" != typeof t ? e : t
                }(this, e.call(this, n)),
                i = /^.+github\.com\/([^/]+)\/?([^/]+)?.*$/.exec(r.base_);
            if (i && 3 === i.length) {
                var o = i[1],
                    a = i[2];
                r.base_ = "https://api.github.com/users/" + o + "/repos", r.name_ = a
            }
            return r
        }
        return function(e, t) {
            if ("function" != typeof t && null !== t) throw new TypeError("Super expression must either be null or a function, not " + typeof t);
            e.prototype = Object.create(t && t.prototype, {
                constructor: {
                    value: e,
                    enumerable: !1,
                    writable: !0,
                    configurable: !0
                }
            }), t && (Object.setPrototypeOf ? Object.setPrototypeOf(e, t) : e.__proto__ = t)
        }(t, e), t.prototype.fetch_ = function() {
            var e = this;
            return function t() {
                var n = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : 0;
                return fetch(e.base_ + "?per_page=30&page=" + n).then(function(e) {
                    return e.json()
                }).then(function(r) {
                    if (!(r instanceof Array)) throw new TypeError;
                    if (e.name_) {
                        var i = r.find(function(t) {
                            return t.name === e.name_
                        });
                        return i || 30 !== r.length ? i ? [e.format_(i.stargazers_count) + " Stars", e.format_(i.forks_count) + " Forks"] : [] : t(n + 1)
                    }
                    return [r.length + " Repositories"]
                })
            }()
        }, t
    }(((r = i) && r.__esModule ? r : {
        default: r
    }).default);
    t.default = o
}, function(e, t, n) {
    "use strict";
    t.__esModule = !0;
    var r, i = n(42),
        o = (r = i) && r.__esModule ? r : {
            default: r
        };
    var a = function() {
        function e(t) {
            ! function(e, t) {
                if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
            }(this, e);
            var n = "string" == typeof t ? document.querySelector(t) : t;
            if (!(n instanceof HTMLAnchorElement)) throw new ReferenceError;
            this.el_ = n, this.base_ = this.el_.href, this.salt_ = this.hash_(this.base_)
        }
        return e.prototype.fetch = function() {
            var e = this;
            return new Promise(function(t) {
                var n = o.default.getJSON(e.salt_ + ".cache-source");
                void 0 !== n ? t(n) : e.fetch_().then(function(n) {
                    o.default.set(e.salt_ + ".cache-source", n, {
                        expires: 1 / 96
                    }), t(n)
                })
            })
        }, e.prototype.fetch_ = function() {
            throw new Error("fetch_(): Not implemented")
        }, e.prototype.format_ = function(e) {
            return e > 1e4 ? (e / 1e3).toFixed(0) + "k" : e > 1e3 ? (e / 1e3).toFixed(1) + "k" : "" + e
        }, e.prototype.hash_ = function(e) {
            var t = 0;
            if (0 === e.length) return t;
            for (var n = 0, r = e.length; n < r; n++) t = (t << 5) - t + e.charCodeAt(n), t |= 0;
            return t
        }, e
    }();
    t.default = a
}, function(e, t, n) {
    var r, i;
    ! function(o) {
        if (void 0 === (i = "function" == typeof(r = o) ? r.call(t, n, t, e) : r) || (e.exports = i), !0, e.exports = o(), !!0) {
            var a = window.Cookies,
                s = window.Cookies = o();
            s.noConflict = function() {
                return window.Cookies = a, s
            }
        }
    }(function() {
        function e() {
            for (var e = 0, t = {}; e < arguments.length; e++) {
                var n = arguments[e];
                for (var r in n) t[r] = n[r]
            }
            return t
        }
        return function t(n) {
            function r(t, i, o) {
                var a;
                if ("undefined" != typeof document) {
                    if (arguments.length > 1) {
                        if ("number" == typeof(o = e({
                                path: "/"
                            }, r.defaults, o)).expires) {
                            var s = new Date;
                            s.setMilliseconds(s.getMilliseconds() + 864e5 * o.expires), o.expires = s
                        }
                        o.expires = o.expires ? o.expires.toUTCString() : "";
                        try {
                            a = JSON.stringify(i), /^[\{\[]/.test(a) && (i = a)
                        } catch (e) {}
                        i = n.write ? n.write(i, t) : encodeURIComponent(String(i)).replace(/%(23|24|26|2B|3A|3C|3E|3D|2F|3F|40|5B|5D|5E|60|7B|7D|7C)/g, decodeURIComponent), t = (t = (t = encodeURIComponent(String(t))).replace(/%(23|24|26|2B|5E|60|7C)/g, decodeURIComponent)).replace(/[\(\)]/g, escape);
                        var c = "";
                        for (var l in o) o[l] && (c += "; " + l, !0 !== o[l] && (c += "=" + o[l]));
                        return document.cookie = t + "=" + i + c
                    }
                    t || (a = {});
                    for (var u = document.cookie ? document.cookie.split("; ") : [], d = /(%[0-9A-Z]{2})+/g, f = 0; f < u.length; f++) {
                        var h = u[f].split("="),
                            p = h.slice(1).join("=");
                        this.json || '"' !== p.charAt(0) || (p = p.slice(1, -1));
                        try {
                            var m = h[0].replace(d, decodeURIComponent);
                            if (p = n.read ? n.read(p, m) : n(p, m) || p.replace(d, decodeURIComponent), this.json) try {
                                p = JSON.parse(p)
                            } catch (e) {}
                            if (t === m) {
                                a = p;
                                break
                            }
                            t || (a[m] = p)
                        } catch (e) {}
                    }
                    return a
                }
            }
            return r.set = r, r.get = function(e) {
                return r.call(r, e)
            }, r.getJSON = function() {
                return r.apply({
                    json: !0
                }, [].slice.call(arguments))
            }, r.defaults = {}, r.remove = function(t, n) {
                r(t, "", e(n, {
                    expires: -1
                }))
            }, r.withConverter = t, r
        }(function() {})
    })
}, function(e, t, n) {
    "use strict";
    (function(e) {
        t.__esModule = !0;
        var n = function() {
            function t(e) {
                ! function(e, t) {
                    if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
                }(this, t);
                var n = "string" == typeof e ? document.querySelector(e) : e;
                if (!(n instanceof HTMLElement)) throw new ReferenceError;
                this.el_ = n
            }
            return t.prototype.initialize = function(t) {
                t.length && this.el_.children.length && this.el_.children[this.el_.children.length - 1].appendChild(e.createElement("ul", {
                    class: "md-source__facts"
                }, t.map(function(t) {
                    return e.createElement("li", {
                        class: "md-source__fact"
                    }, t)
                }))), this.el_.dataset.mdState = "done"
            }, t
        }();
        t.default = n
    }).call(t, n(0))
}, function(e, t, n) {
    "use strict";
    t.__esModule = !0;
    var r, i = n(45),
        o = (r = i) && r.__esModule ? r : {
            default: r
        };
    t.default = {
        Toggle: o.default
    }
}, function(e, t, n) {
    "use strict";
    t.__esModule = !0;
    var r = function() {
        function e(t) {
            ! function(e, t) {
                if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
            }(this, e);
            var n = "string" == typeof t ? document.querySelector(t) : t;
            if (!(n instanceof Node)) throw new ReferenceError;
            this.el_ = n, this.active_ = !1
        }
        return e.prototype.update = function() {
            var e = window.pageYOffset >= this.el_.children[0].offsetTop + -43;
            e !== this.active_ && (this.el_.dataset.mdState = (this.active_ = e) ? "hidden" : "")
        }, e.prototype.reset = function() {
            this.el_.dataset.mdState = "", this.active_ = !1
        }, e
    }();
    t.default = r
}]));