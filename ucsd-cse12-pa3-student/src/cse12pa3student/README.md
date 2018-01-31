1. This could happen. In the case "##S_  if a StackWorklist is used, in the empty square below   
								   ##__
								   ##_#
								   ##F#"
'S', SetPrevious would be called twice.

2. Yes, QueueWorklist always produce a path shorter or equals to the length of StackWorklist, because QueueWorklist visit every available neighbor and therefore can always find a short path to the finish square.

3. The statement is false. In the case "S___ , StackWorklist visit less Squares than QueueWork
										____
										____
										___F"
list.