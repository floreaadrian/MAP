using System;
namespace ToyLanguage.Exceptions
{
    public class FileAlreadyUsed : Exception
    {
        public FileAlreadyUsed()
        : base("File Already Used")
        {
        }
    }
}
