using System;
namespace ToyLanguage.Model
{
    public interface IDict<Key,Value>
    {
        void add(Key key, Value val);
        bool isDefined(Key id);
        void update(Key id, Value val);
        Value lookup(Key id);
        void clear();
    }
}
