package net.yank0vy3rdna_and_Iuribabalin.Commands;

import net.yank0vy3rdna_and_Iuribabalin.App.Dispatcher;

import java.nio.ByteBuffer;

/**
 * Команда выхода из программы
 */
public class ExitCommand implements Executable {
    @Override
    public String exec(OutputCommand outputCommand, Dispatcher dispatcher) {
        //dispatcher.stop();
        return "Работа в консоли закончена\n" +
                "              `..``.` ``                      \n" +
                "            `.```  `...````                   \n" +
                "          `..` `...```.``..`                  \n" +
                " ..   `. `  `:oyyy/  `...````                 \n" +
                "`sso//syo:.. .+yyy/-.  `.`````                \n" +
                " :yyysoooo+. `.--`  .```... ``                \n" +
                "  .:osssyys/-`  ..``.-`  `..``                \n" +
                "  :/yyysoosys-``.--   .``....                 \n" +
                " :ysoosssosys:--  ..`...-` `.`            ``` \n" +
                "  `.o/osso//` .-....-`  .--.           .``...`\n" +
                "       `-``-....-.  `-.```.` `..`  ````...` `.\n" +
                "       .......   ...``.`  .-..``.```..` `..`..\n" +
                "    .....-`  `.```.`  `-.```.` `...` `.``...` \n" +
                " ``....   .````.   ...` ``  ...` `.````..  ```\n" +
                "`..   `````.`   ..```.` `..`  `.``..`   .`.``.\n" +
                "` ..`````   ..```.`  .-.` `.```..   `````..  `\n" +
                ".....   `.`````  `--`  .```.-.  `.````.   ...`\n" +
                "..  ..`````   ...` `.``.--`  ..``.-`  `...`.. \n" +
                "``.....`  `..` `.` `.-`  ..``.-.  `-....-.    \n" +
                " `-.` `...``.` `...   .``..-   .--..--  .`    \n" +
                "   `.-.`..``...` `..`.-:.  `:-..--   `        \n" +
                "        `...``......-` `-.....`               ";
    }
}
