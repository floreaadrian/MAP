using System;
using System.IO;
using ToyLanguage.Ctrl;
namespace ToyLanguage.Model
{
    public class RunExampleCommand : Command
    {
        private Controller ctrl;

        public RunExampleCommand(String key, String desc, Controller ctr) : base(key,desc)
        {
            this.ctrl = ctr;
        }

        public override void execute()
        {
            String filePath = ctrl.getFilePath();
            FileStream fs = File.OpenWrite(filePath);
            TextWriter writer = new StreamWriter(fs);
            writer.Write("");
            writer.Close();
            ctrl.allStep();
        
        }
    }
}
