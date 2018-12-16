grammar RichRail;

// Rules
command         : newcommand | addcommand | getcommand | delcommand | remcommand;
newcommand      : newtraincommand | newwagoncommand;
newtraincommand : 'new' 'train' ID;
newwagoncommand : 'new' 'wagon' componenttype ID ('weight' weight)? ('specialvalue' specialvalue)?;
addcommand      : 'add' ID 'to' ID;
getcommand      : 'getnumseats' type id=ID;
delcommand      : 'delete' type ID;
remcommand      : 'remove' ID 'from' ID;
type            : 'train' | 'wagon';
componenttype   : 'locomotief' | 'passagiercomponent' | 'vrachtcomponent';
weight          : NUMBER;
specialvalue    : NUMBER;

// Tokens
ID          : ('a'..'z')('a'..'z'|'0'..'9')*;
NUMBER      : ('0'..'9')+;
WHITESPACE  : [ \t\r\n\u000C] -> skip;