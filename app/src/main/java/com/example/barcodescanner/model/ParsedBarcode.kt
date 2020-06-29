package com.example.barcodescanner.model

import com.example.barcodescanner.model.schema.*
import com.example.barcodescanner.model.schema.Calendar

class ParsedBarcode(barcode: Barcode) {
    val id = barcode.id
    val text = barcode.text
    val formattedText = barcode.formattedText
    val format = barcode.format
    val schema = barcode.schema
    val date = barcode.date
    val errorCorrectionLevel = barcode.errorCorrectionLevel

    var firstName: String? = null
    var lastName: String? = null
    var organization: String? = null
    var jobTitle: String? = null
    var address: String? = null

    var email: String? = null
    var emailSubject: String? = null
    var emailBody: String? = null

    var emailType: String? = null
    var secondaryEmail: String? = null
    var secondaryEmailType: String? = null
    var tertiaryEmail: String? = null
    var tertiaryEmailType: String? = null

    var phone: String? = null
    var phoneType: String? = null
    var secondaryPhone: String? = null
    var secondaryPhoneType: String? = null
    var tertiaryPhone: String? = null
    var tertiaryPhoneType: String? = null

    var smsBody: String? = null

    var networkAuthType: String? = null
    var networkName: String? = null
    var networkPassword: String? = null

    var bookmarkTitle: String? = null
    var url: String? = null
    var googlePlayUrl: String? = null
    var youtubeUrl: String? = null
    var geoUri: String? = null

    var eventUid: String? = null
    var eventStamp: String? = null
    var eventOrganizer: String? = null
    var eventStartDate: Long? = null
    var eventEndDate: Long? = null
    var eventSummary: String? = null

    var receiptType: Int? = null
    var receiptTime: String? = null
    var receiptFiscalDriveNumber: String? = null
    var receiptFiscalDocumentNumber: String? = null
    var receiptFiscalSign: String? = null
    var receiptSum: String? = null


    init {
        when (schema) {
            BarcodeSchema.BOOKMARK -> parseBookmark()
            BarcodeSchema.EMAIL -> parseEmail()
            BarcodeSchema.GEO -> parseGeoInfo()
            BarcodeSchema.GOOGLE_PLAY -> parseGooglePlay()
            BarcodeSchema.CALENDAR -> parseCalendar()
            BarcodeSchema.MMS,
            BarcodeSchema.SMS -> parseSms()
            BarcodeSchema.MECARD -> parseMeCard()
            BarcodeSchema.PHONE -> parsePhone()
            BarcodeSchema.VCARD -> parseVCard()
            BarcodeSchema.WIFI -> parseWifi()
            BarcodeSchema.YOUTUBE -> parseYoutube()
            BarcodeSchema.URL -> parseUrl()
            BarcodeSchema.RECEIPT -> parseReceipt()
        }
    }

    private fun parseBookmark() {
        val bookmark = Bookmark.parse(text) ?: return
        bookmarkTitle = bookmark.title
        url = bookmark.url
    }

    private fun parseEmail() {
        val email = Email.parse(text) ?: return
        this.email = email.email
        emailSubject = email.subject
        emailBody = email.body
    }

    private fun parseGeoInfo() {
        geoUri = text
    }

    private fun parseGooglePlay() {
        googlePlayUrl = text
    }

    private fun parseCalendar() {
        val calendar = Calendar.parse(text) ?: return
        eventUid = calendar.uid
        eventStamp = calendar.stamp
        eventOrganizer = calendar.organizer
        eventSummary = calendar.summary
        eventStartDate = calendar.startDate
        eventEndDate = calendar.endDate
    }

    private fun parseSms() {
        val sms = Sms.parse(text) ?: return
        phone = sms.phone
        smsBody = sms.message
    }

    private fun parsePhone() {
        phone = Phone.parse(text)?.phone
    }

    private fun parseMeCard() {
        val meCard = MeCard.parse(text) ?: return
        firstName = meCard.firstName
        lastName = meCard.lastName
        address = meCard.address
        phone = meCard.phone
        email = meCard.email
    }

    private fun parseVCard() {
        val vCard = VCard.parse(text) ?: return
        
        firstName = vCard.firstName
        lastName = vCard.lastName
        organization = vCard.organization
        jobTitle = vCard.title
        url = vCard.url
        geoUri = vCard.geoUri
        
        phone = vCard.phone
        phoneType = vCard.phoneType
        secondaryPhone = vCard.secondaryPhone
        secondaryPhoneType = vCard.secondaryPhoneType
        tertiaryPhone = vCard.tertiaryPhone
        tertiaryPhoneType = vCard.tertiaryPhoneType

        email = vCard.email
        emailType = vCard.emailType
        secondaryEmail = vCard.secondaryEmail
        secondaryEmailType = vCard.secondaryEmailType
        tertiaryEmail = vCard.tertiaryEmail
        tertiaryEmailType = vCard.tertiaryEmailType
    }

    private fun parseWifi() {
        val wifi = Wifi.parse(text) ?: return
        networkAuthType = wifi.auth
        networkName = wifi.name
        networkPassword = wifi.password
    }

    private fun parseYoutube() {
        youtubeUrl = text
    }

    private fun parseUrl() {
        url = text
    }

    private fun parseReceipt() {
        val receipt = Receipt.parse(text) ?: return
        receiptType = receipt.type
        receiptTime = receipt.time
        receiptFiscalDriveNumber = receipt.fiscalDriveNumber
        receiptFiscalDocumentNumber = receipt.fiscalDocumentNumber
        receiptFiscalSign = receipt.fiscalSign
    }
}