package com.danaku

class KontakModel {
    var namalengkap: String? = null
    var notelp: String? = null
    var kantor: String? = null
    var email: String? = null
    var alamat: String? = null
    var fb: String? = null
    var ig: String? = null
    var wa: String? = null
    var key: String? = null

    constructor()

    constructor(namalengkap: String?, notelp: String?, kantor: String?, email:String?,
                alamat:String?, fb:String?, ig:String?, wa:String?) {
        this.namalengkap = namalengkap
        this.notelp = notelp
        this.kantor = kantor
        this.email = email
        this.alamat = alamat
        this.fb = fb
        this.ig = ig
        this.wa = wa




    }
}