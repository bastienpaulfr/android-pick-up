package fr.bipi.sample.pickup.ui.common

interface BaseView {
    /**
     * Ask view to show an error according to the throwable given in parameter.
     *
     * It is the responsibility of the view to determine the best way to display an error. Minimum effort
     * would be a simple log.
     */
    fun showError(t: Throwable)

    /**
     * Ask view to display a message to the operator.
     *
     * It is the responsibility of the view to determine the best way to display a message. Minimum effort
     * would be a simple log.
     */
    fun showMessage(resId: Int, vararg args: Any)
}
