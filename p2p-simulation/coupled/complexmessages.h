#ifndef MESSAGEUTIL
#define MESSAGEUTIL

#define TTLOFFSET 1000000
#define	PEERIDOFFSET 1000

//some functions to manage the messages passed between different components, where the integer must contain several values coded together.

/*this returns a message (type long) as passed between different components, from the peerid, messageid, and ttl that are given
 * */
static long buildMessage(int id, int peer, int ttl){
	return (long) ttl * TTLOFFSET + peer*PEERIDOFFSET + id;
}
static long buildMessage(int id, int peer){
	return buildMessage(id, peer,0);
}
static int getPeerId(long msg){
	//extract TTL
	int TTL = (int) floor(msg/TTLOFFSET);
	//reuse to extract peerid
	int peerid = (int) floor((msg - (TTL *TTLOFFSET))/PEERIDOFFSET);
	return peerid;
}

static int getTTL(long msg){
	//extract TTL
	int TTL = (int) floor(msg/TTLOFFSET);
	return TTL;
}

static int getMessageId(long msg){
	//extract TTL
	int TTL = (int) floor(msg/TTLOFFSET);
	//reuse to extract peerid
	int peerid = (int) floor((msg - (TTL *TTLOFFSET))/PEERIDOFFSET);
	int msgid = msg - (peerid *PEERIDOFFSET + (TTL*TTLOFFSET));
	return msgid;

}

static Time makeTimefromSeconds(float ssec){

	if (ssec<0) return Time(0);
	int hours=0, mins=0, seconds=0, mils=0;
	float copyval = ssec;
	float w;
	//hours
	w= floor(ssec/3600);
	hours = static_cast<int>(w);
	copyval = copyval -(w*3600);
	//cout<<copyval<<endl;
	//minutes
	w = floor(copyval/60);
	mins = static_cast<int>(w);
	copyval = copyval -(w*60);
	//cout<<copyval<<endl;
	//seconds
	w =floor(copyval);
	seconds = static_cast<int>(w);
	copyval = copyval - w;
	//cout<<copyval<<endl;
	//millisec
	w = floor(copyval *1000);
	mils = static_cast<int>(w);

	if (hours >32767)//max value for time in hours, cf mytime.h
		hours = 32767;

	if(hours<0 || mins<0 || seconds<0|| mils<0){//problem ?
		cout<<"time must convert :"<<ssec<<endl;
		cout<<"obtained :"<<hours<<" :"<<mins<<" :"<<seconds<<" :"<<mils<<endl;
	}

	return Time(hours,mins,seconds,mils) ;
}

//pair of message + time to put messages in a priority queue
class TimedMessage {

public :
	TimedMessage(long message, long time){
		msg=message;
		outtime = time;
	}

	inline long getOuttime() const{
		return outtime;
	}

	inline long getmsg() const{
		return msg;
	}

private :
	long msg;
	long outtime;


};

//comparator for the above
struct TMComp {
  bool operator()( const TimedMessage& m1,
    const TimedMessage& m2 ) const
    {
	  if( m1.getOuttime() > m2.getOuttime() ) //higher priority for the earlier output time
		  return true;
	  else
		  return false;

    }
};

#endif
