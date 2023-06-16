package org.example.control;

import org.example.client.Constants;

/**             In dieser Klasse wird die Zeitmessung der einzelnen Methoden gesteuert.
 * @author      Philipp Hennken
 * @version     18.0.2
 */
public class Timer
{
    private static long startTime = System.nanoTime();

    /**         Diese Methode startet die Zeitmessung. Dafür wird die aktuelle Zeit in Nanosekunden als Startzeit gesetzt.
     * @pre     //TODO
     * @post    Die Zeitmessung wurde gestartet.
     */
    public static long start()
    {
        return startTime = System.nanoTime();
    }

    public static int calculatePassedMinutesSinceLastMethodCall()
    {
        long a = (System.nanoTime() - startTime) / Constants.NANO_TO_MILLI;
        return (int) (a);
    }
}
