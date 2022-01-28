package fr.bipi.sample.pickup.app.error

import android.content.Context
import fr.bipi.sample.pickup.app.extensions.toResString

/**
 * Class representing an error that can be displayed to the user of application.
 *
 * Error message is made by a title, a message and message arguments. Title and message are strings.xml ids.
 *
 */
@Suppress("MemberVisibilityCanBePrivate")
open class AppException : Exception {
    /**
     * Message title
     */
    val titleId: Int
    /**
     * Message string id
     */
    val messageId: Int
    /**
     * Message arguments if any
     */
    val arguments: Array<out Any>

    constructor(titleId: Int, messageId: Int, vararg objects: Any) : super() {
        this.titleId = titleId
        this.messageId = messageId
        this.arguments = objects
    }

    /**
     * Build this class and uses context to make a string to be passed to mother class
     */
    constructor(context: Context, titleId: Int, messageId: Int, vararg objects: Any) :
        super("${titleId.toResString(context)}: ${messageId.toResString(context, *objects)}") {
            this.titleId = titleId
            this.messageId = messageId
            this.arguments = objects
        }

    /**
     * Build this class and uses context to make a string to be passed to mother class
     */
    constructor(context: Context, cause: Throwable, titleId: Int, messageId: Int, vararg objects: Any) :
        super("${titleId.toResString(context)}: ${messageId.toResString(context, *objects)}", cause) {
            this.titleId = titleId
            this.messageId = messageId
            this.arguments = objects
        }

    fun getOperatorMessage(context: Context): String {
        return messageId.toResString(context, *arguments)
    }

    fun getOperatorTitle(context: Context): String {
        return titleId.toResString(context)
    }

    fun getOperatorFullMessage(context: Context): String {
        return "${titleId.toResString(context)}: ${messageId.toResString(context, *arguments)}"
    }
}
