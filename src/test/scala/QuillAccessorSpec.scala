/*
 * Copyright 2018 lre
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.time.OffsetDateTime

import default.cassandratest.cassandra.EntityDataStructure.LucasDocument
import default.cassandratest.cassandra.LucasEnums.LucasEntities.{ Addresses, Hobbies }
import default.cassandratest.cassandra.QuillAccessor
import org.scalatest.{ FreeSpec, MustMatchers }

import scala.concurrent.Await
import scala.concurrent.duration._

class QuillAccessorSpec extends FreeSpec with MustMatchers {

  val db = QuillAccessor("quill.local")

  "createTables should create 2 tables" in {
    val created = Await.result(db.createTables(), 30.seconds)
  }

  "add 2 rows in addresses entity" in {
    val ldoc01 = LucasDocument(
      id = "address01",
      alias = "Fetscherstraße 57",
      tags = Set("street", "dresden"),
      createdAt = OffsetDateTime.now,
      updatedAt = OffsetDateTime.now
    )
    val insert01 = Await.result(db.addDocument(Addresses, ldoc01), 30.seconds)
    val ldoc02 = LucasDocument(
      id = "address02",
      alias = "Schachtweg 6d",
      tags = Set("street", "badf"),
      createdAt = OffsetDateTime.now,
      updatedAt = OffsetDateTime.now
    )
    val insert02 = Await.result(db.addDocument(Addresses, ldoc02), 30.seconds)
  }

  "add 2 rows in hobbies entity" in {
    val ldoc03 = LucasDocument(
      id = "hobby01",
      alias = "Coding",
      tags = Set("computer", "skill"),
      createdAt = OffsetDateTime.now,
      updatedAt = OffsetDateTime.now
    )
    val insert03 = Await.result(db.addDocument(Hobbies, ldoc03), 30.seconds)
    val ldoc04 = LucasDocument(
      id = "hobby02",
      alias = "Running",
      tags = Set("sport", "freetime"),
      createdAt = OffsetDateTime.now,
      updatedAt = OffsetDateTime.now
    )
    val insert04 = Await.result(db.addDocument(Hobbies, ldoc04), 30.seconds)
  }

  "should list two address entity rows" in {
    val addresses = Await.result(db.getDocuments(Addresses), 30.seconds)
    println(addresses)
    addresses.length mustBe (2)
  }

  "should list two hobbies entity rows" in {
    val hobbies = Await.result(db.getDocuments(Hobbies), 30.seconds)
    println(hobbies)
  }
}
