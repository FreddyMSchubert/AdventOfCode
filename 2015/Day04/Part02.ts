// https://gist.github.com/basarat/90cf19f5f13db6213fcb1d1292f4e931
import * as crypto from 'crypto';
export const md5 = (contents: string) => crypto.createHash('md5').update(contents).digest("hex");

for (let i = 0; ; i++) {
	const hash = md5("iwrupvqb" + i);
	if (hash.startsWith("000000")) {
		console.log(i);
		break;
	}
}
