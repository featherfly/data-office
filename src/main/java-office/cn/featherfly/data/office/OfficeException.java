
package cn.featherfly.data.office;

import java.util.Locale;

import cn.featherfly.common.exception.LocalizedException;

/**
 * <p>
 * OfficeException
 * </p>
 *
 * @author 钟冀
 */
public class OfficeException extends LocalizedException {

    private static final long serialVersionUID = 3514407639691650897L;

    /**
     * @param message message
     * @param locale  locale
     * @param ex      ex
     */
    public OfficeException(String message, Locale locale, Throwable ex) {
        super(message, locale, ex);

    }

    /**
     * @param message message
     * @param locale  locale
     */
    public OfficeException(String message, Locale locale) {
        super(message, locale);

    }

    /**
     * @param message message
     * @param argus   argus
     * @param locale  locale
     * @param ex      ex
     */
    public OfficeException(String message, Object[] argus, Locale locale, Throwable ex) {
        super(message, argus, locale, ex);

    }

    /**
     * @param message message
     * @param argus   argus
     * @param locale  locale
     */
    public OfficeException(String message, Object[] argus, Locale locale) {
        super(message, argus, locale);

    }

    /**
     * @param message message
     * @param argus   argus
     * @param ex      ex
     */
    public OfficeException(String message, Object[] argus, Throwable ex) {
        super(message, argus, ex);

    }

    /**
     * @param message message
     * @param argus   argus
     */
    public OfficeException(String message, Object[] argus) {
        super(message, argus);

    }

    /**
     * @param message message
     * @param ex      ex
     */
    public OfficeException(String message, Throwable ex) {
        super(message, ex);

    }

    /**
     * @param message message
     */
    public OfficeException(String message) {
        super(message);

    }

    /**
     * @param ex ex
     */
    public OfficeException(Throwable ex) {
        super(ex);

    }

}
