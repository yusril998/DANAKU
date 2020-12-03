package com.danaku

class KontakModel {
    var namalengkap: String? = null
    var notlp: String? = null
    var key: String? = null

    constructor()

    constructor(namalengkap: String?, notlp: String?){
        this.namalengkap = namalengkap
        this.notlp = notlp
    }
}