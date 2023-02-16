Blacklist Name Matching

Background
Banks are responsible for stopping money laundering. Hence it is necessary to avoid money
transfers to terrorists and criminals. The list of such persons is published by EU:
http://eeas.europa.eu/cfsp/sanctions/consol-list/index_en.htm
Task
Your task is to implement simple algorithm to compare given name against blacklist to detect such
transfers. In case of partial match, algorithm should return as few "false positive" matches as
possible. Implement in any language that you feel most comfortable in.
Your program should accept 3 arguments (they can also be hard-coded in the source code):
• Argument 1 is the name to validate against blacklist
• Argument 2 is the input file name that contains one blacklisted name per line
• Argument 3 is the input file name that contains one noise word per line
Your program should find and print out all the matching names from blacklist.
For example the arguments could be:
name = "Osama Bin Laden" (this name is validated against names in names_file)
names_file = names.txt (blacklist)
noise_file = noise_words.txt (noise words that are not relevant)
Example:
There is blacklisted name: "Osama Bin Laden". Your program should detect at least following user
entries:
- "Osama Laden"
- "Osama Bin Laden"
- "Bin Laden, Osama"
- "Laden Osama Bin"
- "to the osama bin laden"
- "osama and bin laden"
etc.
In real world, name matching is not a trivial task. Matches could happen also based on:
• Substring matches - bl: "Robert", user: "Bert"
• Phonetic matching - bl: "Cairns", user: "Kearns" vs "Kerns"
• Ignoring spelling errors - bl: "Madis", user: "Madus"
• Abbreviations - bl: "Joe Luis Webb", user: "Joe L. Webb"

To provide some inspiration we provide a link to a software that does all those strategies and more:
http://www.basistech.com/text-analytics/rosette/name-indexer/#
