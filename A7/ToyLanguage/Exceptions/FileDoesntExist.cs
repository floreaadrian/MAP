using System;
namespace ToyLanguage.Exceptions
{
    public class FileDoesntExist : Exception
    {
        public FileDoesntExist()
        : base("File Doesnt Exist")
        {
        }
    }
}
