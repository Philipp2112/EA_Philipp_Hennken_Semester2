package org.example.control;

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
        long a = (System.nanoTime() - startTime) / 1_000_000_000;
        return (int) (a);
    }
}
