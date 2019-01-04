using System;
namespace ToyLanguage.Model
{
    public class ExitCommand :Command
    {
        public ExitCommand(String key, String desc) : base(key,desc)
        {
        }

        public override void execute()
        {
            System.Environment.Exit(1);
        }
    }
}
