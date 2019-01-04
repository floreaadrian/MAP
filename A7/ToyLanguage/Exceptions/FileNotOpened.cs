using System;
namespace ToyLanguage.Exceptions
{
    public class FileNotOpened : Exception
    {
        public FileNotOpened()
        : base("File Not Opened")
        {
        }
    }
}
