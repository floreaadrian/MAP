using System;
using System.Collections.Generic;
using System.IO;
using ToyLanguage.Model;

namespace ToyLanguage.View
{
    public class TextMenu
    {
        private Dictionary<String, Command> commands;

        public TextMenu()
        {
            commands = new Dictionary<String, Command>();
        }

        public void addCommand(Command c)
        {
            commands.Add(c.getKey(), c);
        }

        private void printMenu()
        {
            foreach (Command com in commands.Values)
            {
                Console.WriteLine(com.getKey() + ". " + com.getDescription());
            }
        }

        public void show()
        {
            while (true)
            {
                printMenu();
                Console.WriteLine("Input the option: "); 
                string key = Console.ReadLine();

                Command com = commands[key];
                if (com == null)
                {
                    Console.WriteLine("Invalid Option");
                    continue;
                }
                else
                {
                    com.execute();
                }

            }
        }
    }
}
